*   [Overview](#overview)
*   [Prerequisites](#prerequisites)
*   [Running](#running)

----
## Overview
This api is a implementation which will be used by process layer api to integrate the location services provided by third part services.  
This api provides following services
* Get nearby Branch / ATM locations list by providing current location coordinates.
* Search Branch / ATM locations by address. Address may be part of the address/ City / State / Zip code
* To get the Branch / ATM location detail by location-id
* To get the list of filters applicable for the location search by co-ordinates api and search by address api.

----
## Prerequisites

* [Download](https://www.oracle.com/technetwork/java/javase/downloads/jdk11-downloads-5066655.html) & install the Java 11 SDK. Ensure [JAVA_HOME](http://docs.oracle.com/cd/E19182-01/820-7851/inst_cli_jdk_javahome_t/index.html) is also set.
* [Download](https://gradle.org/install/) Gradle.

----
## Running

To build the application go to root folder and use `gradlew build` in command prompt.

To run the application use `gradlew bootRun` in command prompt.

Once the instance is running the endpoint will be at `http://localhost:8086/systems/v1/`

The end point can be tested directly via the web browser through swagger documentation `http://localhost:8086/swagger-ui.html` which contains the details of all four apis. 

HTTP client can also be used to submit GET requests.
Recommended tools include:
* The Postman plugin for Google Chrome (https://chrome.google.com/webstore/detail/postman-rest-client/fdmmgilgnpjigdojojpjoooidkmcomcm?hl=en)
* The REST client for Firefox (https://addons.mozilla.org/de/firefox/addon/restclient/)

If you prefer to use a standalone container then the JAR file can be found in build/libs folder.
Use java -jar aub-system-service-0.0.1-SNAPSHOT.jar to run the application.a

