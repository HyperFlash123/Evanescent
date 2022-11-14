# Evanescent
 
This application is a simple way to exercise your Right to Erasure.

**Prerequisites:** [Java 8](http://www.oracle.com/technetwork/java/javase/downloads/jdk8-downloads-2133151.html) and [Node.js](https://nodejs.org/).

## Getting Started

To install this application, run the following commands:
```
git clone https://github.com/HyperFlash123/Evanescent.git
cd evanescent
```

This will get a copy of the project installed locally.

### Steps to create your own database

1. Download [sqlite3](https://www.sqlite.org/download.html)

2. In your terminal or with your favorite IDE, create a table named Evanescent with the following columns: user, website, username, password, deleted. (All should be varchar(32)).
```
CREATE TABLE EVANESCENT
(user varchar(32) default NULL, 
website varchar(32) default NULL, 
username varchar(32) default NULL, 
password varchar(32) default NULL, 
deleted varchar(32) default "False",
PRIMARY KEY (username));

INSERT INTO EVANESCENT
VALUES (@user, @website, @username, @password, @deleted);
```

3. Insert values for a valid account in the table for any of the supported platforms.

4. In the Database.java file, inside the connect() method, change the url to the full path of your database.

Sample Database:
![Sample Database](https://i.postimg.cc/HLStxQ9q/Screen-Shot-2022-10-26-at-9-49-05-PM.png)

5. Download necessary dependencies for the Selenium automation: 

To run the server, cd into the `server` folder and run:
 
```
./mvnw spring-boot:run
```

To run the client, cd into the `client` folder and run:
 
```
npm install && npm start
```

The application can be accessed at http://localhost:4200/
## License

Apache 2.0, see [LICENSE](LICENSE).
