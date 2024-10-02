from pyspark.sql import SparkSession
from pyspark.sql.functions import from_json, col
from pyspark.sql.types import StructType, StructField, StringType, TimestampType

# Spark 세션 생성
spark = SparkSession.builder \
    .appName("ClickEventProcessing") \
    .config("spark.jars.packages", "org.apache.spark:spark-sql-kafka-0-10_2.12:3.3.0") \
    .config("spark.sql.extensions", "org.apache.spark.sql.kafka010.KafkaSourceProvider") \
    .getOrCreate()

# Kafka 및 PostgreSQL 설정
kafka_broker = "kafka-broker-local:29092"
postgres_host = "postgres"
postgres_port = "5432"
postgres_db = "postgres"
postgres_user = "username"
postgres_password = "password"

# 클릭 이벤트 스키마 정의
click_event_schema = StructType([
    StructField("user_id", StringType(), True),
    StructField("ad_id", StringType(), True),
    StructField("timestamp", TimestampType(), True)
])

# Kafka에서 클릭 이벤트 스트림 읽기
click_events = spark \
    .readStream \
    .format("kafka") \
    .option("kafka.bootstrap.servers", kafka_broker) \
    .option("subscribe", "click_events") \
    .load() \
    .select(from_json(col("value").cast("string"), click_event_schema).alias("data")) \
    .select("data.*")

# PostgreSQL에서 사용자 정보 읽기
jdbc_url = f"jdbc:postgresql://{postgres_host}:{postgres_port}/{postgres_db}"
user_info = spark.read.format("jdbc") \
    .option("url", jdbc_url) \
    .option("dbtable", "users") \
    .option("user", postgres_user) \
    .option("password", postgres_password) \
    .option("driver", "org.postgresql.Driver") \
    .load()

# 클릭 이벤트와 사용자 정보 조인
joined_data = click_events.join(user_info, "user_id")

# 결과 처리 (콘솔에 출력)
query = joined_data \
    .writeStream \
    .outputMode("append") \
    .format("console") \
    .start()

query.awaitTermination()