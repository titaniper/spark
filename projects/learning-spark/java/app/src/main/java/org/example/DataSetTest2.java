package org.example;

import org.apache.spark.sql.*;
import org.apache.spark.api.java.function.FilterFunction;
import org.apache.commons.lang3.RandomStringUtils;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class DataSetTest2 {
    public void test() {
        SparkSession spark = null;
        try {
            spark = SparkSession.builder()
                    .appName("pyspark-notebook")
                    .master("spark://localhost:8341")
                    .config("spark.driver.bindAddress", "0.0.0.0")
                    .config("spark.ui.port", "4041")
                    .config("spark.ui.showConsoleProgress", "false")
                    .config("spark.driver.host", "localhost")
                    .getOrCreate();

            // 랜덤 데이터 생성을 위한 Random 객체
            Random rand = new Random();
            rand.setSeed(42);

            // Person 객체의 리스트 생성
            List<Person> data = new ArrayList<>();

            // 1000개의 Person 인스턴스 생성
            for (int i = 0; i < 1000; i++) {
                data.add(
                        new Person(
                                "user" + RandomStringUtils.randomAlphanumeric(5),
                                rand.nextInt(100) // 0-99 사이의 랜덤 나이
                        ));
            }

            // 명시적 인코더 생성
            Encoder<Person> personEncoder = Encoders.bean(Person.class);
            

            // Person 형태의 데이터셋 생성
            Dataset<Person> dsPerson = spark.createDataset(data, personEncoder);

            // 데이터셋 출력
            System.out.println("Person Dataset:");
            dsPerson.show();

            // 데이터셋에 대한 작업 수행 예시 (FilterFunction 명시적 사용)
            Dataset<Person> filteredDs = dsPerson.filter((FilterFunction<Person>) person -> person.getAge() > 30);
            System.out.println("Filtered Dataset (Age > 30):");
            filteredDs.show();

            System.out.println("Spark UI is available at http://localhost:4041");
            System.out.println("Press Enter to exit the program");
            System.in.read();

        } catch (Exception e) {
            System.err.println("An error occurred: " + e.getMessage());
            e.printStackTrace();
        } finally {
            if (spark != null) {
                spark.stop();
            }
        }
    }

    // Person 클래스는 그대로 유지
    public static class Person implements java.io.Serializable {
        private String name;
        private int age;

        public Person() {
        }

        public Person(String name, int age) {
            this.name = name;
            this.age = age;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public int getAge() {
            return age;
        }

        public void setAge(int age) {
            this.age = age;
        }
    }
}