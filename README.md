

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
