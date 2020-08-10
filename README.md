# Cinema-project
# Content list
* [Project purpose](#purpose)
* [Project dependencies](#dependencies)
* [Startup instructions](#startup)
* [Author](#author)

# <a name="purpose"></a>Project purpose
Cinema booking system with basic set of functionality required for its operation.
<hr/>
Any user can register or log in without prior authentication.
Role-based access control system is available for authorized users.
Users with 'USER' role can complete orders and add tickets to shopping cart.
And only 'ADMIN' role-based users can adding new movies, movie sessions and cinema halls.
<hr/>

# <a name="dependencies"></a>Project dependencies
* Java SE Development Kit: 11
* Maven: 3.6.3
* maven-checkstyle-plugin: 3.1.1
* maven-war-plugin: 3.2.3
* maven-compiler-plugin: 3.8.0
* javax.servlet-api: 3.1.0
* jstl: 1.2
* taglibs standard: 1.1.2
* jackson-databind: 2.11.0
* commons-dbcp2: 2.7.0
* mysql-connector-java: 8.0.17
* lombok: 1.18.12
* hibernate-core, hibernate-java8: 5.4.5.Final
* log4j: 1.2.17
* spring-core, spring-context, spring-orm, spring-webmvc: 5.2.6.RELEASE
* spring-security-core, spring-security-config, spring-security-web: 5.3.3.RELEASE
* jakarta.validation-api: 2.0.2
<hr>

# <a name="#startup"></a>Startup instructions
* prerequisites: JDK SE 11+, Tomcat, MySQL
1. Open the project in your IDE.
2. Add Java SDK in Project structure.
3. Configure Tomcat:
  3.1. add the artifact cinema-project:war exploded;
  3.2. add as URL http://localhost:8080/
4.  At src.main.resources.db.properties use your username 
   and password for your MySQL Workbench to create a Connection.
5. Open MySQL Workbench and run this line to create a schema: CREATE SCHEMA `cinema` DEFAULT CHARACTER SET utf8.
6. Set your path to log file in src.main.resources.log4j.properties.
7. Run the project.
8. Testing API, for authorization, you have to add 
a new header, where Authorization is key and Basic
 email:password is value, where email:password 
 must be encoded to Base64 format.

By default, InjectDataController provides adding 
two users into database:
first user already registered with 'ADMIN' role (email = "pythonadmin@google.com", password = "meshuggah") and the
                                                 second one - user with 'USER' role (email = "userjava@yahoo.com", password = "lofofora").
These credentials can be changed. It's up to you!

# <a name="author"></a>Author  

Oleksii Lehedza: https://github.com/olehedza                                               
