### JdbcCrudConsoleApp

[![Build Status](https://app.travis-ci.com/jmoloko/JdbcCrudConsoleApp.svg?branch=main)](https://app.travis-ci.com/jmoloko/JdbcCrudConsoleApp)
[![codecov](https://codecov.io/gh/jmoloko/JdbcCrudConsoleApp/branch/main/graph/badge.svg?token=WF48JU6HL6)](https://codecov.io/gh/jmoloko/JdbcCrudConsoleApp)

Console CRUD application that interacts with the database and allows you to perform all CRUD operations on entities:
* Team (id, name, List <Developer> developers)
* Developer (id, firstName, lastName, List <Skill> skills)
* Skill (id, name)

**Requirements:**
* MVC pattern.
* For database migration, https://www.liquibase.org/ was used.
* The service layer of the application is covered by unit tests (junit + mockito).
* Maven is used to import libraries.

**Technologies:** _Java, MySQL, JDBC, Maven, Liquibase, JUnit, Mockito_