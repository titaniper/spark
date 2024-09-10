package org.example;

import org.apache.spark.sql.Dataset;
import org.apache.spark.sql.Row;
import org.apache.spark.sql.SparkSession;
import org.apache.spark.sql.types.DataTypes;
import org.apache.spark.sql.types.Metadata;
import org.apache.spark.sql.types.StructField;
import org.apache.spark.sql.types.StructType;
import org.apache.spark.sql.RowFactory;

import java.util.Arrays;

public class App {
    public static void main(String[] args) {
        // SparkSession 생성
        SparkSession spark = SparkSession
            .builder()
            .appName("Java Spark SQL Example")
            .master("local[*]")
            .getOrCreate();

        // 샘플 데이터 생성
        Dataset<Row> df = spark.createDataFrame(
            Arrays.asList(
                RowFactory.create("John", 25),
                RowFactory.create("Alice", 30),
                RowFactory.create("Bob", 28)
            ),
            new StructType(new StructField[]{
                new StructField("name", DataTypes.StringType, false, Metadata.empty()),
                new StructField("age", DataTypes.IntegerType, false, Metadata.empty())
            })
        );

        // 임시 뷰 생성
        df.createOrReplaceTempView("people");

        // SQL 쿼리 실행
        Dataset<Row> sqlDF = spark.sql("SELECT * FROM people WHERE age > 27");

        // 결과 출력
        sqlDF.show();

        // SparkSession 종료
        spark.stop();
    }
}