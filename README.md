# WebShop

#Technologies: 
  - Java 8
  - Spring MVC - divide business logic, view and controller
  - Hibernate - access DB
  - JSP/JSTL - markup
  - MySQL - DB Server
  - Bootstrap - UI
  - JQuery, Dropzone, Select2, Slick - Processing user actions
  - Log4J - logging errors, debug info
  - AJAX - Asynchronous requests 
  - JUnit - unit testing
  - Spring Security - registration/authorization
  - Spring mail - sending mails
  - GSON - converting from/to JSON
  - Guava - partial using for simplifying code 
  - Maven - Build system
  - Tomcat - Server
  
#Used design patterns:
  - [Bridge]( https://github.com/Vova2805/WebShop/tree/master/src/main/java/com/webshop/service) - dividing abstraction and implementation
  - [Decorator](https://github.com/Vova2805/WebShop/tree/master/src/main/java/com/webshop/model/classes/extra). - own implementation. Overloading some features of base class.Implement ability for define image src for derived classes which have images. If image is not existed - get default.
  - [Filter](https://github.com/Vova2805/WebShop/tree/master/src/main/java/com/webshop/util/filterFacade) - implementation filtering objects by different criterias. 
  - Facade - implement a single point of entry for using filtering.
  - MVC pattern - divide business logic, views and controllers
  - [Data access objects](https://github.com/Vova2805/WebShop/tree/master/src/main/java/com/webshop/dao) pattern - implementing data access through objects. 
