# SOXStore-Server

## Overview
Currently, pub/sub-architecture is used to publish multiple users efficiently.
In our research, we use the "SOXFire" platform based on the XMPP protocol. (https://github.com/MSec-H2020/Secure_SOXFire)  
A SOXFire can immediately publish data to multiple subscribers, but this server cannot store the data on it.  
Therefore, if the other applications want to use the previous data on the SOXFire, the developer has to implement the function to store data from SOXFire.  
SOXStore-server can solve this problem.  This application uses to adapt the SOXFire.  
This system has some functions, subscribing, storing, and searching, so a developer can easily manage data without implementing the complex function to connect SOXFire.

## System Architecture
The system architecture of SOXStore-server shows the following.
This system performs as a web server.
First, you confirm that you want to connect to the SOXFire, finally, you run this application on your machine.
SOXStore-Server only uses the HTTP protocol to connect clients, so you can operate it easily.
In addition, as shown in the figure, the system can connect to another platform/application.
The details of commands show the following section.
<img width="1097" alt="soxstoreserver" src="https://user-images.githubusercontent.com/13267712/140923418-4f1814df-da6b-451b-8431-9eb78df82af5.png">

## Environment:
    runtime: java 14
    Database: MySQL 8.0.19 (Maybe 5.7.xx ver. available)

## Use file:
    sox-store-server-v.1.1.0.jar (update -> implemented new endpoint)
    
    create_database.txt (update -> new table and modify Topic Table's column)
    
    application.properties (not update, no change)


## Usage:
### Activation
   1. Please update or re-create Tables on "SOX_STORE" Schema. (Sorry, I forget table name regulation, maybe want you to use "lowercase".)

      If you success update the SOX_STORE schema, you can show a new table "server_info" and a new column "server_name" on "topic".
     
   2. Please execute the command to activate the sox-store-server!

   Server Start Command:

       java -Dspring.config.location=./conf/application.properties -jar sox-store-server-v.1.1.0.jar
       this server start up localhost:8080

   -DSpring.config.location option orders DB connection information file.

   You can change the content of the application.properties, you can connect your DB.

   -- application properties -- 
   spring.datasource.url=jdbc:mysql://localhost:3306/db_schema_name
   spring.datasource.username=db_username
   spring.datasource.password=db_password
   spring.datasource.driver-class-name=com.mysql.cj.jdbc.Driver


### All command explain

Subscirbe Command:

parameters:
    server_address -- This parameter orders the SOXFire address what you want to connect.
    
    sox_user       -- This parameter orders the SOXFire username
    
    sox_pass       -- This parameter orders the SOXFire password
    
    keyword        -- This parameter orders the "prefix matching mode" (optional parameter) (keyword=prefix)
    
    node_name      -- This parameter orders the node name what you want to get. This parameter can be ordered multiple. (ex. node_name=test_device1&node_name=test_device2 OK)

This server registers the device once and continues to permanently store the published data from SOXFire in a DB until the server down.


Get list of all topic name:

parameters:

    server_address -- This parameter orders the SOXFire address what you want to connect.
    
    sox_user       -- This parameter orders the SOXFire username
    
    sox_pass       -- This parameter orders the SOXFire password
  
    This command doesn't have "node_name" parameter because this command only uses to get names of all devices.


Get detail of node

parameters:

    server_address -- This parameter orders the SOXFire address what you want to connect.
    
    sox_user       -- This parameter orders the SOXFire username
    
    sox_pass       -- This parameter orders the SOXFire password
    
    node_name      -- This parameter orders the node name what you want to get. This parameter can be ordered multiple. (ex. node_name=test_device1&node_name=test_device2 OK)
