# WebShop

[Quick view](https://www.youtube.com/watch?v=x_efAIwwYz8&ab_channel=VovaDudas)

Technologies: 
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
  
Used design patterns:
  - [Bridge]( https://github.com/Vova2805/WebShop/tree/master/src/main/java/com/webshop/service) - dividing abstraction and implementation
  - [Decorator](https://github.com/Vova2805/WebShop/tree/master/src/main/java/com/webshop/model/classes/extra). - own implementation. Overloading some features of base class.Implement ability for define image src for derived classes which have images. If image is not existed - get default.
  - [Filter](https://github.com/Vova2805/WebShop/tree/master/src/main/java/com/webshop/util/filterFacade) - implementation filtering objects by different criterias. 
  - Facade - implement a single point of entry for using filtering.
  - MVC pattern - divide business logic, views and controllers
  - [Data access objects](https://github.com/Vova2805/WebShop/tree/master/src/main/java/com/webshop/dao) pattern - implementing data access through objects. 

<img width="764" alt="screen shot 2017-09-27 at 12 43 29 am" src="https://user-images.githubusercontent.com/11577478/30886330-4dcbfe78-a31f-11e7-8ba8-85a5e7134756.png">
<img width="767" alt="screen shot 2017-09-27 at 12 42 18 am" src="https://user-images.githubusercontent.com/11577478/30886326-4d9bab2e-a31f-11e7-9239-017d18b304c9.png">
<img width="767" alt="screen shot 2017-09-27 at 12 42 36 am" src="https://user-images.githubusercontent.com/11577478/30886327-4db8b124-a31f-11e7-8395-3da88b39aa6b.png">
<img width="763" alt="screen shot 2017-09-27 at 12 42 51 am" src="https://user-images.githubusercontent.com/11577478/30886328-4dc826b8-a31f-11e7-97bc-ee6a6a172034.png">
<img width="765" alt="screen shot 2017-09-27 at 12 43 12 am" src="https://user-images.githubusercontent.com/11577478/30886329-4dc9c162-a31f-11e7-8142-f25dc0adb4b9.png">

