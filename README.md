# Distributed-Systems-Assignment
A remote, asynchronous dictionary lookup service developed with Servlets/JSPs and Java RMI.


## Table of Contents

+ [Brief Explaination](#brief_explaination)
+ [Technologies](#technologies)
+ [How to Run](#how-to-run)

## Brief Explaination

The JSP page should provide users with the ability to specify a string which will be checked against the dictionary. The HTML form information is dispatched to a servlet on submission that adds the client request to an in-queue. The web client polls the web server periodically (every 10 seconds) and queries if the request has been processed. Client requests in the inQueue should be periodically removed and processed (every 10 seconds).

I have a client worker thread running asynchronously processing the request objects in the inQueue. The request objects are abstract can be specialised into different concrete implementations for the different requests, i.e. Search, Add, etc.

The methods add, modify, search and delete are all implemented in the remote object on the RMI server - however, they have not all been implemented on the client side. You can see from the setup of the client side that a new implementation of a request can be easily integrated.

The server side is as expected, mainly consisting of the service interface and it's implementation - hidden from the user.

## Technologies

- Java RMI Framework
- Servlet/JSP Framework

## How to Run

In the CMD, direct to the folder where the JAR file is located and run the following command:

```
    java -jar dictionary-service.jar
```

If all goes well the CMD shout output "server ready".

Paste the WAR file into the webapps folder of your Tomcat directory.

In a separate CMD, locate the bin folder of the Tomcat Server Folder
and enter "startup" to activate the Tomcat server.

Open a browser and go to:

```
    localhost:8080/job-server
```

The application should be up and running now!

-----

__*Tara O'Kelly - G00322214@gmit.ie*__