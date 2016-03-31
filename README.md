This is a simple REST web service that creates and retrieves birds.

1) Build the project using the top level pom file under rest-poject using-
mvn clean install

2) Start the web application on default Tomcat port(8080) from the webapp project using-
mvn spring-boot:run

It provides the following operations.

1)Create bird-
REST endpoint- http://localhost:8080/birds
Sample JSON POST Request:-

{
"name":"Yogesh",
"family":"Yog",
"continents":["FLY","WEB"],
"firstAppearanceDate":"2013-01-08",
"visible":true
}

2)Get all birds
REST endpoint-
http://localhost:8080/birds


3)Get one bird 
REST endpoint-
http://localhost:8080/birds/{id}

4) Delete Bird
REST endpoint-
http://localhost:8080/birds/{id}
