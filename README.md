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



install-dependency org.eclipse.osgi:org.eclipse.osgi:3.7.1.R37x_v20110808-1106  --dir=lib
install-dependency org.eclipse.virgo.util:org.eclipse.virgo.util.parser.manifest:3.5.0.M04  --dir=lib
install-dependency org.eclipse.virgo.util:org.eclipse.virgo.util.osgi.manifest:3.5.0.M04  --dir=lib
install-dependency ch.qos.logback:logback-classic:1.0.6  --dir=lib
install-dependency org.slf4j:slf4j-api:1.6.6  --dir=lib

