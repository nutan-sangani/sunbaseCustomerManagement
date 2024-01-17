## Description
### This an customer Management app built using React as frontend, spring boot for rest apis and mysql for database.

## Features :
1. Authentication using JWT.
2. Has feature to add, delete, update, retreive customers from the database
3. Has feature to sync data with a remote server, using the sync button on the bottom of the getCustomer page

## Things that it lack :
1. Tests. (could not implement, due to time constraint and lack of knowledge of testing in java).
2. Error handling on frontend as well as backend to convey meaning full error messages (could not implement due to time constraints).
3. Register functionality has not been implemented, since it was not one of the requirements.


## How to start the app
0.  Clone the repo to your local pc using the command : "git clone https://github.com/nutan-sangani/sunbaseCustomerManagement.git"
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
    
3.  Than open the server and go to the application.properties and enter your secret and mysql username, dbname, password
4.  Once all this is configured and you have maven, java, mysql installed on your pc, you can go to the server/customerManagement directory and execute :
    a. ./mvnw clean install (to install all dependencies for spring boot server).
    b. ./mvnw spring-boot:run (to start the server).
5.  Then to start the frontend, in other terminal go to the client/client/ directory and execute :
    a. npm install (to install react dependencies)
    b. npm start (to start react)
6.  Now you can go to http://localhost:3000 to test the project.

## General Structure of the Project :
1. We have 2 tables, one (customers table) to store customer data and other (userCredential table) to store the data of the user (Admin or the person who has access to all the customers data).
2. Data in userCredential table can be inserted manually or we can add register feature (currently not there).
3. So when the user login's he gets a jwt, which is stored in his localStorage and which is sent on all further request to customer routes.
4. To sync the data with the remotesServer, we use the user's id and pass in our userCredential table, to get an authentication token from the remote server mentioned in the assignment doc. With this auth token(which is seperate from our own jwt token) we send request to remoteServer. For this, it is necessay that userCredentials are same on both the databases, ie the remoteServer database and our database.

I have created a POSTMAN collection for testing all the api's, you can email me your email id, so that i can send the collection, so that testing can be done on your end (my email is nutan.sangani.work@gmail.com)

ScreenShots of the Website :

### Home page :

![image](https://github.com/nutan-sangani/sunbaseCustomerManagement/assets/116813161/3664c716-195c-41a8-a003-cca72354c837)

### Login page :

![image](https://github.com/nutan-sangani/sunbaseCustomerManagement/assets/116813161/96418db9-452c-44ba-8084-1b543bb5e568)

### Add customers page :

![image](https://github.com/nutan-sangani/sunbaseCustomerManagement/assets/116813161/a0a42265-b4d8-4ab2-8b9b-66d461254ded)

### Search Customers page : 

![image](https://github.com/nutan-sangani/sunbaseCustomerManagement/assets/116813161/fc23537a-92db-479b-a8bd-bc150aff924a)

### Update Customer page :

![image](https://github.com/nutan-sangani/sunbaseCustomerManagement/assets/116813161/c18463f8-3706-468f-a7e4-3d4d739232d3)

### All customers after syncing :

![image](https://github.com/nutan-sangani/sunbaseCustomerManagement/assets/116813161/9b81bcdd-fb91-4d0c-8234-4dd97282855c)



