Manny Fiesta
============

A tool for scanning jar manifests & detail reporting.

Goal
----
Provide details on the manifests for cross referencing OSGi configuration and dependencies.



Run
---
- _mvn clean install jetty:run_
  - OR
- _mvn clean install tomcat7:run_



Mon: 02-Jul-12
--------------
- Initial path scanning working and loading details in the database.



TODO
----
 - Create page for imported and exported packages by Bundle
 - After Jars have been selected - click "Analyze"
 - Compare the Host-Bundle to export-registry
 - Compare the Import-Bundle to Bundles
 - Compare the Requires-Bundle to Bundles
 - Compare the "uses" directive to export-registry



Notes
-----
- Eclipse Virgo bundle utilities used for parsing manifest details
- Logging done with SLF4J and Logback
  - src/main/resources/logback.xml - app log config
  - src/main/resources/logback-access.xml - Server access log and HTTPReq/Resp Logging
- JMX
  - JMX is enabled in Maven Jetty & Tomcat plugins
  - Spring JMX configuration is enabled
- Elvyx
  - configuration is included - NOT working!
- MariaDB (better fork of MySql)
  - DB Setup in this file
  - DB config in src/main/resources/META-INF/spring/database.properties
- Maven Site Plugin
  - Configured for most reporting features
- Enforcer Maven Plugin
  - Blocks old versions of Spring
  - Blocks older versions of SLF4J
  - Blocks log4j - take off the training wheels and use Logback
  - Blocks older versions of projects, where one with org.someproj is available



MYSQL/MariaDB Setup
-------------------
- $
  - _mysql -u root -p_
- mysql>
  - _CREATE DATABASE manny;_
  - _GRANT ALL PRIVILEGES ON manny.* TO manny@localhost IDENTIFIED BY 'password' WITH GRANT OPTION;_
  - _GRANT ALL PRIVILEGES ON manny.* TO manny@127.0.0.1 IDENTIFIED BY 'password' WITH GRANT OPTION;_
  - _FLUSH PRIVILEGES;_



Quotes
- Never trust a man in a wheelchair with dirty shoes
- Never trust a man with chips, where you don't see a game being played
- Never trust a man with a riding crop and no horse


