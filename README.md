# MySQL Setup

### Download MySQL (5.6) 

- http://dev.mysql.com/downloads
- Download MySQL Community server
- Install MYSQL -> Developer Default 

### Verify Installation 
- In MySQL Workbench
  - Username: root
  - Password: node
  - Port: 3306
  - Hostname: 127.0.0.1

### Create a new Connection/User
- Connect to the DB as a root user (using the password that you used during installation: 
- Open and run the script called : **“01-create-user.sql”**
- This script creates a new MySQL user for the App:
  - userId: hbstudent
  - Pass: hbstudent
- To test if this user is created correctly, close the current connection(user: root) in Workbench and create a new connection:
  - Connection Name: hbstudent
  - UserName: hbstudent
  - Click: Test Connection


### Create a new DB:
- Connect to the DB as user = hbstudent 
- Open and run this script: **“02-stundet-tracker.sql”**
- This script will create a new DB called hb-student-tracker
- Inside that DB, it will create a new table called “student”
- Refresh the Schemas to see the created DB/Table

---

# Setup Hibernate in Eclipse:
- Create an Eclipse Project
- Create a new Java project: file -> New -> Java Project -> “hibernate-demo”
- Right click on Project name -> new -> Folder -> “lib”

### Download Hibernate jar Files:
- Go to: https://hibernate.org/
- Click on Hibernate ORM
- Download hibernate-release-5.5.5.final
- Extract the zip file
- Copy all the files in: **hibernate-release-5.5.5.Final\lib\required**
- Paste them in eclipse project under the lib folder

### Download MYSQL JDBC Driver:
- Go to:  https://dev.mysql.com/
- My SQL Downloads -> MySQL Community Downloads -> Connector/J
- Download **Platform Independent** zip file and copy this file under the eclipse project lib folder:
- **mysql-connector-java-8.0.26\mysql-connector-java-8.0.26\mysql-connector-java-8.0.26.jar**

### Add JAR Files to the Eclipse Project (Build Path)
- Right click on the Eclipse project name -> Properties -> Java Build Path -> Libraries -> Add Jars
- Select All the files under the hibernate-demo/lib folder -> Apply
- A new folder called “Referenced Libraries” will be created inside your project that contains all the added files

### Testing your JDBC Connection:
- Run “TestJdbc"






