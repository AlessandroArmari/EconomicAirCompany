STEPS I FOLLOWED TO CREATE THIS PROJECT:


1. CREATE A JAVA-MAVEN-SPRING PROJECT

dependecies selected:
{
-Spring Boot DevTools
-Spring Web
-Spring Data jpa
-MySQL Driver
}

2. OPEN SQL AND CREATE A SCHEMA SQL 

3. IMMEDIATELY MODIFY application.properties as below:

spring.jpa.properties.hibernate.dialect = org.hibernate.dialect.MySQLDialect
spring.datasource.url=jdbc:mysql://localhost:3306/economicair  +++HERE TYPE THE NAME OF YOUR SQL SCHEMA+++  
spring.datasource.username=root    +++YOUR SQL username+++
spring.datasource.password=666Bruce666     +++YOUR SQL password+++


spring.jpa.hibernate.ddl-auto=create    +++THIS IS IMPORTANT, CHECK application.properties FOR MORE+++
spring.jpa.show-sql=true
spring.jpa.properties.hibernate.format_sql=true
+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++

4. CREATE MODEL AND FILES WITHIN

5. CREATE REPOSITORY AND FILES WITHIN

6. CREATE SERVICE AND FILES WITHIN

7. CREATE CONTROLLER AND FILES WITHIN

8. Create DataLoadRunner.java AND DECIDE if you want to fill the SQL tables by java or by URL command (PostMan)

9. OPEN PostMan AND create THE http MEHODS you find in the file: "NEW_Economic_Air_Company.postman_collection"

10.HAVE FUN WITH THE FLIGHTS YOU WILL NEVER TAKE!!!  :(
