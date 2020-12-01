# M1-JEE-Project - Interns Supervision Solution (I2S)

## Table of contents

- [Informations](#Informations)

- [Features](#Features)

- [Installation](#Installation)

- [Tests](#Tests)

- [Use of the website](#use_of_the_website)

## Informations

### Team Composition

Group composed of : 

- Mathieu Cantagrel
- Théo Delettre
- Vincent Dubois
- Vincent Mouillon
- Karsten Roy
- Victor Tang  ...

Group : SE2

### Description

JEE Web Application that help any teacher at EFREI Paris supervise easily the interns assigned to him/her.

### Technologies/framework used

Built with

- Java
- CSS
- Javascript
- JEE Plateform

IDE 

- IntelliJ IDEA  

Database

- PostgreSQL

Application server

- Glassfish 5

But also...

- Maven

- Docker

- SonarQube

- JUnit and Mockito

- JMeter

## Features

This project enables any teacher to supervise the internships of his student.
He can :

- login with his Efrei email address and his password
- see, on the homepage, all the internship assigned to him
- filter this internship by years, name of the student and keywords.
- update the information of the internship on the homepage
- see the details of the internship on the mission details page
- update the information of the internship on the mission details page
- logout

## Installation

### Versions

#### Version 1

Use the branch `version1`. You are able to run a deployed version using `docker-compose up -d` once the project is built using Maven.
Before building it, comment the first line of the [db.properties](https://github.com/nerstak/M1-JEE-Project/blob/main/src/main/webapp/WEB-INF/db.properties) file, and uncomment the second.
You don't need to setup the database or the server.
However, it may be a bit unstable, as it was not the application server used during development.

If you wish to use Glassfish, follow the instructions below.

#### Version 2

Use the branch `main`. Because of the differences between Glassfish and Wildfly as application servers, you cannot use the same setup. Please follow the instructions below.

### Database

#### Set up of PostgreSQL with Glassfish

For more information on how to use Glassfish and PostgreSQL, please read the following [documentation](https://github.com/nerstak/M1-JEE-Project/blob/main/doc/Use%20Postgress%20with%20Glassfish.md).

#### Set up of the database with PostgreSQL

For more information on how to set up our database, please read the following [documentation](https://github.com/nerstak/M1-JEE-Project/blob/main/doc/Setup%20database%20with%20PostgreSQL.md).

#### Connection to the database with Java

The login, password and url connection to the database are stored inside the [db.properties](https://github.com/nerstak/M1-JEE-Project/blob/main/src/main/webapp/WEB-INF/db.properties) file.   
Those strings are used in the DataServices classes, for the version 1.

For the version 2, credentials are stored inside the [persistence.xml](https://github.com/nerstak/M1-JEE-Project/blob/main/src/main/resources/META-INF/persistence.xml) file.

### Building

`mvn package`

## Tests

Note that tests are only available in the Version 2.

### Unit testing coverage

Unitary tests have been set up for most of our code base. The only part not tested are the models (EJB), because they are only beans with no complexity.

To run the tests, use `mvn clean package`. 

Every information related to these tests will be displayed in the terminal.

![maven_test_terminal](./Screenshot/maven_junit_terminal.png)

### Static testing

SonarQube has been set up to provide some static testing on the software. It provides informations on potential bugs, security risks and mistakes. Thanks to this tool, we have been able to reduce errors. To use it, please see this [documentation](https://github.com/nerstak/M1-JEE-Project/blob/main/doc/Setup%20of%20sonarqube.md).

![Sonarqube_review](./Screenshot/sonarqube_review.png)

### JMeter testing

JMeter has been used to have some performance tests. However, while you have JMeter file located inside the `jmeter` folder that is working, you may not be able to use it: it has been built with different UUIDs for the data. Some screenshots have been placed inside the report. To use it, please see this [documentation](https://github.com/nerstak/M1-JEE-Project/blob/main/doc/Using%20JMeter.md)

![jmeter_table](./Screenshot/jmeter_table.png)

## Use of the website <a name = "use_of_the_website"></a>

### Login

You first need to login in the login page :
![login page](./Screenshot/LoginPage.JPG)

If you make a mistake in your credentials or if you let field empty, an error message will be displayed.  
![Error empty fields](./Screenshot/ErrorEmptyFields.JPG)  

![Error bad credentials](./Screenshot/ErrorCredentials.JPG)

### Homepage

Once you are logged in, you have access to some actions on the home page.  
![Homepage](./Screenshot/Homepage.JPG)
First, you have an overview of all the interns that are assigned to you in the table.  
You can modify every information and validate your change by clicking on the "modify" button at the beginning of the line.  
![Table](./Screenshot/Internship.JPG)
Also, you can see more details about an internship by clicking on the "details" button on each line. This will redirect you to the mission details page.  
You can filter the internships by the year, keywords or name of the intern and validate your choice by clicking on the "Search button".  
![Filter](./Screenshot/Filter.JPG)  
Last, you can logout of your account by clicking on the logout button placed in the top right corner, just right next to your name. You will be redirect to the login page.  

### Details

On the mission details page, you will be able to modify other information.
Company :

- Name of the company

- Intern supervisor inside the company

- Begin date

- End date

- Address of the company   
  ![Company](./Screenshot/Company.JPG) 

Student :

- Group

- First name

- Last name

- Email

- URL of the linkedin profile   
  ![Student](./Screenshot/Student.JPG)   

Internship :

- Report's title

- Description

- Your comments

- The student comments  
  ![Internship](./Screenshot/InternshipDetails.JPG)   

Skills and keywords : 

- Add keywords

- Add skills  
  ![Skills and keywords](./Screenshot/Skills_keywords.JPG)   

You must click on the "modify" or "add" button of each section you have modified to validate your changes.
A message will be displayed each time you modify something to keep you inform about the changes. 

![Error database](./Screenshot/Error_db.JPG)  

![Success database](./Screenshot/Success_db.JPG)  
