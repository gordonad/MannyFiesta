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
 - After Jars have been selected - click "Analyze"
 - Compare the Host-Bundle to export-registry
 - Compare the Import-Bundle to Bundles
 - Compare the Requires-Bundle to Bundles
 - Compare the "uses" directive to export-registry



Notes
-----
- Eclipse Virgo bundle utilities used for parsing manifest details
- JMX
  - JMX is enabled in Maven Jetty & Tomcat plugins
  - Spring JMX configuration is enabled
- Elvyx
  - configuration is included - NOT working!



MYSQL/MariaDB Setup
-------------------
- $
  - _mysql -u root -p_
- mysql>
  - _CREATE DATABASE manny;_
  - _GRANT ALL PRIVILEGES ON manny.* TO manny@localhost IDENTIFIED BY 'password' WITH GRANT OPTION;_
  - _GRANT ALL PRIVILEGES ON manny.* TO manny@127.0.0.1 IDENTIFIED BY 'password' WITH GRANT OPTION;_
  - _FLUSH PRIVILEGES;_


