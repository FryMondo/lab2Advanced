# Lab2 Advanced Repository
## Виконав
- Студент групи ІА-22
- Птачик Роман
- Варіант: 15 (С3 = 0)
## How to launch project
### Clone the repository:
```
git clone https://github.com/FryMondo/lab2Advanced.git
```
### Running project
If you use IDE, open project and run ***AnnotationReflectionDemo.java***
### Running project via console
Move to the directory
```
lab2Advanced/src
```
Run next commands
```
javac *.java
java AnnotationReflectionDemo
```
## Project Structure
### AnnotationReflectionDemo.java
Executes the main program demonstrating the functionality of SQL query generation using annotations and reflection.
- Creates three different classes (User, Product, SimpleCustomer) with annotated and non-annotated fields.
- Demonstrates the generation of SQL queries (CREATE, INSERT, UPDATE, DELETE) for annotated classes using the SQLQueryGenerator class.
- Compares the execution time of SQL query generation with and without reflection.
### SQLQueryGenerator.java
A utility class for generating SQL queries (CREATE, INSERT, UPDATE, DELETE) based on annotated classes.
- generateCreateQuery: Generates a CREATE TABLE SQL query based on field-to-column mappings provided by annotations.
- generateInsertQuery: Generates an INSERT INTO SQL query with values extracted from the object's fields.
- generateUpdateQuery: Generates an UPDATE SQL query using the object's fields and a specified condition.
- generateDeleteQuery: Generates a DELETE FROM SQL query with a specified condition.
- Utilizes Java Reflection to access annotations and retrieve metadata from class fields.
### Annotations
- @Table: Maps a class to a database table. Contains a name attribute specifying the table name.
- @Column: Maps a field to a database column. Contains a name attribute specifying the column name.
### User.java
Represents a user entity with the following fields:
- id: Integer, annotated with @Column(name = "id").
- name: String, annotated with @Column(name = "name").
- email: String, annotated with @Column(name = "email").
- Demonstrates field-to-column mapping for SQL query generation.
### Product.java
Represents a product entity with the following fields:
- id: Integer, annotated with @Column(name = "id").
- name: String, annotated with @Column(name = "name").
- price: Double, annotated with @Column(name = "price").
- Shows how annotations can handle different field types for SQL generation.
### SimpleCustomer.java
Represents a customer entity with the following fields:
- id: Integer.
- name: String.
- phone: String.
- Demonstrates manual SQL query generation without using annotations or reflection. Provides a generateInsertQuery method to create an INSERT query for the class.
## Features
- Annotation-Driven SQL Mapping: Uses custom annotations (@Table, @Column) for field-to-database mapping.
- Reflection-Based Metadata Access: Leverages Java Reflection to dynamically generate SQL queries based on class metadata.
- Manual vs Reflection Comparison: Measures execution time for SQL query generation with and without reflection.
- Flexible Query Generation: Supports CREATE, INSERT, UPDATE, and DELETE queries for any annotated class.
## Limitations
- Assumes all fields are of type VARCHAR(255) for CREATE TABLE queries.
- Does not support complex data types or relationships (e.g., foreign keys, constraints).
- Requires all annotated fields to have non-null values for INSERT and UPDATE queries.
