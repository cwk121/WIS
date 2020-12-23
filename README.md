# WIS
Warehouse Inventory System (Spring Boot demo project)  

## Getting Started
1. Install JDK 11, MySQL
2. Create database and database user
```bash
mysql> create database wis; -- Creates the new database
mysql> create user 'wis'@'%' identified by 'wispassword'; -- Creates the user
mysql> grant all on wis.* to 'wis'@'%'; -- Gives all privileges to the new user on the newly created database
```
3. Clone this Repository
```bash
git clone https://github.com/cwk121/WIS
cd WIS
```
4. Run the Application 
```bash
./mvnw spring-boot:run
```
5. Open in browser
```
http://localhost:8080/
```
#### Build the Application and Run
```bash
./mvnw clean package
cd target
java -jar wis-0.0.1-SNAPSHOT.jar
```
## Datasets
Some datasets in csv format is placed in `dataset` folder, you can upload these csv files in the upload page  
The `product_large.csv` contains ~30000 rows of data.

## Functions
- Product page:
  - Search by code or name
  - Pagination, each page showing 100 rows of data
- Inventory page:
  - Transfer inventory
  - Search by location or productCode
  - Pagination, each page showing 100 rows of data
- Upload page:
  - Upload product csv
  - Upload inventory csv
  
## Screenshots
Product page
![Product page](/screenshots/product.png)
Inventory page
![Inventory page](/screenshots/inventory.png)
Upload page
![Upload page](/screenshots/upload.png)

## Daily task
#### Day 1
- Study Spring Boot (file structure, database connection, different annotations, etc...)
- Design data structure and system logic
- Implement UI using JPA
- All required functions are basically done
#### Day 2
- Move the searching function from frontend to backend considering the scalability and bandwidth problems
- Implement pagination (100 rows/page)
- Refactor code with simpler or better practice
- Improve functionalities
- Improve UI
- Write unit test with @SpringBootTest and @DataJpaTest
#### Day 3
- More testing
- Complete the README.md
