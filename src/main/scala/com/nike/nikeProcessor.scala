package com.nike
import org.apache.spark.sql.SparkSession
import org.apache.spark.sql.functions.{col, sum}


object nikeProcessor extends App{

  override def main(arg: Array[String]) {

    val spark: SparkSession = SparkSession
      .builder()
      .appName("Spark SQL basic example")
      .config("spark.some.config.option", "some-value")
      .config("spark.master", "local")
      .getOrCreate()

    val df_store = spark.read.format("csv").option("header", "true").option("inferSchema", "true").load("/home/macit/nike/dataengineertest/store.csv").as("dfstore")
    val df_calendar = spark.read.format("csv").option("header", "true").option("inferSchema", "true").load("/home/macit/nike/dataengineertest/calendar.csv").as("dfcalendar")
    val df_product = spark.read.format("csv").option("header", "true").option("inferSchema", "true").load("/home/macit/nike/dataengineertest/product.csv").as("dfproduct")
    val df_sales = spark.read.format("csv").option("header", "true").option("inferSchema", "true").load("/home/macit/nike/dataengineertest/sales.csv").as("dfsales")



    val df_merged = df_sales.join(df_store, col("dfsales.storeId") === col("dfstore.storeid"), "left")
      .join(df_calendar, col("dfsales.dateId") === col("dfcalendar.datekey"), "left")
      .join(df_product, col("dfsales.productId") === col("dfproduct.productid"), "left").as("dfmerged")


    val df_grouped = df_merged.groupBy("dfmerged.weeknumberofseason").agg( sum("dfmerged.netSales").as("NetSales"),sum("dfmerged.salesUnits").as("salesUnit"))

    df_grouped.write.json("dataengineertest/consumption.json")
  }
}

