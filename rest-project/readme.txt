This is a simple REST web service that creates and retrieves super heroes.
It provides the following operations.

1)Create super hero-
http://ec2-user@ec2-52-36-155-50.us-west-2.compute.amazonaws.com:8080/webapp/superheroservice/superhero

Sample JSON POST Request:-

{
"name":"Yogesh",
"pseudonym":"Yog",
"publisher":"PHANTOM",
"skills":["FLY","WEB"],
"allies":["Spiderman","Batman"],
"firstAppearanceDate":"2013-01-08"
}

2)Get all super heroes
http://ec2-user@ec2-52-36-155-50.us-west-2.compute.amazonaws.com:8080/webapp/superheroservice/allheroes

3)Get super hero by name
http://ec2-user@ec2-52-36-155-50.us-west-2.compute.amazonaws.com:8080/webapp/superheroservice/superhero/<name>
Sample GET request:- http://ec2-user@ec2-52-36-155-50.us-west-2.compute.amazonaws.com:8080/webapp/superheroservice/superhero/Yogesh
