# School Shorts
A re-coding of the outdated "[SchoolHomework website](https://sourceforge.net/projects/schoolhomework/)" - a junior school home practice and games website, based on newer concepts microservices architecture and Jakarta EE.

## Microservice Components (When completed)
1. School Shorts - 
2. SchoolDataAPI (Persistance API) -
3. MariaDB Database Storage -

## Using the Application
Application can be used in 2 ways:
1. Either build it with Maven then run the JAR file, OR
2. Run the pre-built OCI Container (Docker container) image.
Both methods are discussed below.

## Using Maven to Build the JAR file
OpenJDK18(or higher) and Maven should be already installed and accessible via Command Line. Use the following command in folder where you have downloaded this code and the 'pom.xml' file is located.
```
mvn -f pom.xml clean package
```
## Running the Application JAR File
You can use Maven to build the Jar file. Once the JAR file is built, you can run it in one of following ways, from the downloaded project folder:
```
java -jar ./target/SchoolShorts-0.0.1-SNAPSHOT.jar
OR
java -jar .\target\SchoolShorts-0.0.1-SNAPSHOT.jar     (ON WINDOWS)
OR
java -jar ./target/SchoolShorts-0.0.1-SNAPSHOT.jar --server.port=80 --server.servlet.context-path=/
OR
java -jar ./target/SchoolShorts-0.0.1-SNAPSHOT.jar --server.port=8091 --server.servlet.context-path=/schoolshorts
```

## Running the Containerized Version - DockerHub or Quay.io
If you do not wish to use Maven and compile yourself then you may use the OCI Container image. You can run the Containerized version as follows.
### From Docker Hub
```
sudo docker run --name schoolshorts01 -d -p 8080:8080 hammadrauf/schoolshorts
OR
sudo docker run --name schoolshorts01 -d -p 80:8080 hammadrauf/schoolshorts
OR
sudo docker run --name schoolshorts01 -d -p 8080:8080 --env CONTEXT_PATH:/ hammadrauf/schoolshorts
OR
sudo docker run --name schoolshorts01 -d -p 8080:8080 --env CONTEXT_PATH:/myCustomContextPath hammadrauf/schoolshorts 

To Stop Docker Container:
sudo docker stop schoolshorts01

To Start it Again:
sudo docker start schoolshorts01
```
### From Quay.io
```
sudo docker run --name schoolshorts01 -d -p 8080:8080 quay.io/hammadrauf/schoolshorts
OR
sudo docker run --name schoolshorts01 -d -p 80:8080 quay.io/hammadrauf/schoolshorts
OR
sudo docker run --name schoolshorts01 -d -p 8080:8080 --env CONTEXT_PATH:/ quay.io/hammadrauf/schoolshorts
OR
sudo docker run --name schoolshorts01 -d -p 8080:8080 --env CONTEXT_PATH:/myCustomContextPath quay.io/hammadrauf/schoolshorts

To Stop Docker Container:
sudo docker stop schoolshorts01

To Start it Again:
sudo docker start schoolshorts01
```
## Open in Browser
After launching the conatiner OR the Maven built JAR file, you can open a browser on the specified port and Context-Path.\
For Example:\
[http://localhost:8080/](http://localhost:8080/) \
OR\
[http://localhost/](http://localhost/) \
OR\
[http://localhost:8091/schoolshorts](http://localhost:8091/schoolshorts) \
OR\
[http://localhost:8080/myCustomContextPath](http://localhost:8080/myCustomContextPath) \

## Test User
Some links are protected by Spring-Security and need authorization. Developement/Testing user details are as follows:\
UserID: user\
Password: password\
.


