#  Nike Engineering Technical 

## BUILD

 ``` 
mvn clean package 
```
this command wil generate the artifact (jar) with the spark-jon

## RUN

Using spark submit : 

```
spark-submit --class com.nike.nikeProcessor target/nikeProcessor-1.0-SNAPSHOT.jar
```

## Questions : 


• **How long did you spend on the coding test? What would you add to your solution if you had more time? If you didn't spend much time on the coding test then use this as an opportunity to explain what you would add.**

Besides setting up the environments, about 2h. Since I've not worked with spark for a while, I needed to remember a couple of things. I would add some checks/tests. Create deployment scripts.

• **What was the most useful feature that was added to the latest version of your chosen language? Please include a snippet of code that shows how you've used it.**
I used the DataframeAPI, which is very close to python pandas library in some way. I've already worked with it. 

• **How would you track down a performance issue in production? Have you ever had to do this the above task with the terabyte of data?**

SparkUI is already a good tool, but we can add layers for monitoring the performance from other providers. 
ALso checking the created DAG can also help you identify some bottlenecks in your datapipeline 

I worked with a bank where the only perfomance monitoring tool we had was the spark logs. 