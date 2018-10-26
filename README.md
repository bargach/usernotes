# UserNotes
Application for notes management

![screenshot](https://raw.githubusercontent.com/bargach/usernotes/master/screenshots/list_of_notes.png)
  
## Technologies:
 - Spring 5 (MVC, Security, JDBC)
 - Bootstrap 4
 - MySQL 5
 - JUnit 5, Mockito
 - Tomcat 7

## How to build:
> mvn package

Artifact is located at `target/usernotes.war`

## How to run:

 1. Run using embedded tomcat. (http://localhost:8080/)
> mvn tomcat7:run
 2. Run using stand-alone tomcat using war file: copy war into webapps folder.

## Prerequisites:

 1. Installed and configured MySQL. By default `localhost:3306/prod` user `root` and password `1`. Sql file to create tables at `db/schema.sql` 
 2. _(optional)_ Installed and configured Tomcat 7 (or higher)