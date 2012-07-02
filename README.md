Manny Fiesta
============

A tool for scanning jar manifests & detail reporting.

Goal
----
Provide details on the manifests for cross referencing OSGi configuration and dependencies.


Mon: 02-Jul-12
--------------
Initial path scanning working and loading details in the database.


TODO
----
 - After Jars have been selected - click "Analyze"
 - Compare the Host-Bundle to export-registry
 - Compare the Import-Bundle to Bundles
 - Compare the Requires-Bundle to Bundles
 - Compare the "uses" directive to export-registry


Notes
-----
* Uses Eclipse Virgo bundle utilities for parsing manifest details
* Elvyx - configuration is included - NOT working yet.


MYSQL/MariaDB Setup
-------------------

$ mysql -u root -p
mysql> CREATE DATABASE manny;
mysql> GRANT ALL PRIVILEGES ON manny.* TO manny@localhost IDENTIFIED BY 'password' WITH GRANT OPTION;
mysql> GRANT ALL PRIVILEGES ON manny.* TO manny@127.0.0.1 IDENTIFIED BY 'password' WITH GRANT OPTION;
mysql> FLUSH PRIVILEGES;
