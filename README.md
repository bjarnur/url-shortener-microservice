# url-shortener-microservice

## General 
 The goal is to create a project that emits shortened URLs for
 any URL that is passed into it. The project should also provide 
 support for users to visit any URLs via shortened URLs that have
 already been created (i.e. taking a shortened URL as a parameter
 the project should redirect to the original URL).
  
 The solution is built as a microservice because we want the design to 
 enable high availability, and because we want to make it possible 
 to scale beyond one machine. 
 
 **Disclaimer**: This is the first microservice
 that I write so probably there are some things that break convention and so 
 on. I still thought writing this as a MS was a good idea, since it helps meeting
 the requirements of availability and scalability. I also just saw it as a 
 good opportunity to learn :)
 
## Project structure
It's a maven project, build with help of [Spring Initializr](https://start.spring.io/).
After downloading the source run `mvn package` to build the service.

## Configuration
Configuration for host system and discovery service can be found in `application.properties`.
After configuration has been set appropriately service should run and register with discovery
service. 

## Verifying the service
The service is easy to test with the Postan browser add-on: https://addons.mozilla.org/en-US/firefox/tag/postman. 

A good idea is to send a PUT request to '/create' containing, for example, the following Json body: 
`{"original": "www.visir.is"}` and examine the response. 

After this has been done a GET request to the '/lookup' endpoint with the correct parameter should 
redirect you to the original address(e.g. `http://localhost:3333/lookup/-732096136`). 

It's also possible to get overview of all registered URLs and their mappings with a GET 
request to '/all-urls'.

## Possible improvements
 The solution is still pretty rough, for example it's currently 
 storing all information in memory. A good opportunity for 
 improvement would be to implement connection to a datasource that could be shared between all instances of the service.
