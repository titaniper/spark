package org.example;

import org.apache.spark.sql.*;
import org.apache.spark.api.java.function.FilterFunction;
import static org.apache.spark.sql.functions.*;

import java.util.Arrays;
import java.util.List;

public class DataSetTest3 {
    public void test() {
        SparkSession spark = null;
        try {
            spark = SparkSession.builder()
                    .appName("Usage Filtering Example")
                    .master("local[*]")
                    .getOrCreate();

            // 샘플 데이터 생성
            List<Usage> data = Arrays.asList(
                new Usage("user1", 800),
                new Usage("user2", 950),
                new Usage("user3", 1100),
                new Usage("user4", 880),
                new Usage("user5", 1200)
            );

            // 데이터셋 생성
            Dataset<Usage> dsUsage = spark.createDataset(data, Encoders.bean(Usage.class));

            // 1. 람다를 사용한 필터링
            FilterFunction<Usage> f = (FilterFunction<Usage>) u -> u.getUsage() > 900;
            
            // 1. 인라인 람다를 사용한 필터링
            System.out.println("Filtered and ordered results using inline Lambda:");
            dsUsage.filter(u -> u.getUsage() > 900)
                   .orderBy(col("usage").desc())
                   .show(5);

            // 2. DataFrame DSL을 사용한 필터링
            System.out.println("Filtered and ordered results using DSL:");
            dsUsage.filter(col("usage").gt(900))
                   .orderBy(col("usage").desc())
                   .show(5);


        } catch (Exception e) {
            System.err.println("An error occurred: " + e.getMessage());
            e.printStackTrace();
        } finally {
            if (spark != null) {
                spark.stop();
            }
        }
    }

    public static class Usage {
        private String userId;
        private int usage;

        public Usage() {}

        public Usage(String userId, int usage) {
            this.userId = userId;
            this.usage = usage;
        }

        public String getUserId() { return userId; }
        public void setUserId(String userId) { this.userId = userId; }
        public int getUsage() { return usage; }
        public void setUsage(int usage) { this.usage = usage; }
    }
}