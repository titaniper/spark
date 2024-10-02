package org.example;

import org.apache.spark.sql.*;
import org.apache.spark.api.java.function.MapFunction;
import org.apache.spark.sql.Encoders;
import org.apache.spark.sql.api.java.UDF1;
import org.apache.spark.sql.expressions.UserDefinedFunction;
import static org.apache.spark.sql.functions.udf;
import org.apache.spark.api.java.function.FilterFunction;

import java.io.Serializable;
import java.util.Arrays;
import java.util.List;

public class DataSetTest {
    public void test() {
        // SparkSession 구성
        SparkSession spark = SparkSession.builder()
                .appName("pyspark-notebook")
                .master("spark://localhost:8341")  // Docker에 매핑된 로컬 포트 사용
                .config("spark.driver.bindAddress", "0.0.0.0")
                .config("spark.ui.port", "4041")
                .config("spark.ui.showConsoleProgress", "false")
                .config("spark.driver.host", "localhost")
                // .master("spark://spark-master:8341")
                .getOrCreate();

        // Person 클래스 정의 (데이터셋의 스키마로 사용될 것임)
        createDataset(spark);

        // SparkSession 종료
        spark.stop();
    }

    // Person 클래스 정의
    public static class Person implements Serializable {
        private String name;
        private int age;

        public Person(String name, int age) {
            this.name = name;
            this.age = age;
        }

        public Person() {
        }


        public String getName() {
            return name;
        }

        public int getAge() {
            return age;
        }
    }

    public void createDataset(SparkSession spark) {
        // 가짜 데이터 생성
        List<Person> personList = Arrays.asList(
                new Person("Alice", 25),
                new Person("Bob", 30),
                new Person("Charlie", 35),
                new Person("David", 40));

        // 데이터셋 생성
        Dataset<Person> personDataset = spark.createDataset(personList, Encoders.bean(Person.class));

        // 데이터셋 출력
        System.out.println("Original Dataset:");
        personDataset.show();

        // 데이터셋 변환 예시: 나이가 30 이상인 사람들만 필터링
        Dataset<Person> filteredDataset = personDataset.filter((FilterFunction<Person>) person -> person.getAge() >= 30);
        System.out.println("Filtered Dataset (Age >= 30):");
        filteredDataset.show();

        // 데이터셋 변환 예시: 각 사람의 이름을 대문자로 변경
        Dataset<String> upperCaseNames = personDataset.map(
                (MapFunction<Person, String>) person -> person.getName().toUpperCase(),
                Encoders.STRING());
        System.out.println("Names in Uppercase:");
        upperCaseNames.show();
    }
}