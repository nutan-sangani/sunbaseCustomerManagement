## Description
### This an customer Management app built using React as frontend, spring boot for rest apis and mysql for database.

## Features
#### 1. My project has all the crud features , like adding, searching, removing, updating customers.

## Features that could not be implemented
#### 1. I could not implement JWT authentication on my app, due to time constraints.
#### 2. I cound not implement proper error handling due to time constraints (i know time shouldnt be an excuse for error handling, but i am quite new to spring boot, thus delay).

## How to start the app
0.  Clone the repo to local pc using " git clone -b master https://github.com/nutan-sangani/sunbaseAssignment.git " (remember to clone master branch)
1.  Open mysql and create 2 table : 
    create table customers (
    id varchar(40) primary key not null,
    fName varchar(40),
    lName varchar(40),
    state varchar(40),
    street varchar(40),
    city varchar(40),
    address varchar(40),
    email varchar(100) unique,
    phone varchar(13)
    );
    
    create table userCredential (
    	login_id varchar(100),
        pass varchar(100)
    );
    
    insert into usercredential (login_id,pass) values ("test@sunbasedata.com","Test@123");
2.  Than open the server and go to the application.properties and enter your secret and mysql username, dbname, password
3.  Once all this is configured and you have maven, java, mysql installed on your pc, you can go to the server/customerManagement directory and execute :
    a. ./mvnw clean install (to install all dependencies for spring boot server).
    b. ./mvnw spring-boot:run (to start the server).
4.  Then to start the frontend, in other terminal go to the client/client/ directory and execute :
    a. npm install (to install react dependencies)
    b. npm start (to start react)
5.  Now you can go to http://localhost:3000 to test the project.
