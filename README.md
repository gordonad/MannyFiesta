Manny Fiesta
============

A tool for scanning jar manifests

Goal:
Provide details on the manifests for cross referencing OSGi configuration and dependencies.


// TODO - Create the BundleRegistry
// TODO - Create Page for uploading jars and jar paths
// TODO - After Jars have been selected - click "Analyze"
// TODO - Code the export-packages registry
// TODO - Compare import-packages to export-registry including version-range
// TODO - Compare the Host-Bundle to export-registry
// TODO - Compare the Import-Bundle to Bundles
// TODO - Compare the Requires-Bundle to Bundles
// TODO - Compare the "uses" directive to export-registry




Misc
-----



MYSQL
-----
CREATE DATABASE manny;
GRANT ALL PRIVILEGES ON manny.* TO manny@localhost IDENTIFIED BY 'password' WITH GRANT OPTION;
GRANT ALL PRIVILEGES ON manny.* TO manny@127.0.0.1 IDENTIFIED BY 'password' WITH GRANT OPTION;
FLUSH PRIVILEGES;
