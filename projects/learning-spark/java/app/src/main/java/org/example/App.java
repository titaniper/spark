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
        DataSetTest2 test = new DataSetTest2();
        test.test();
    }
}