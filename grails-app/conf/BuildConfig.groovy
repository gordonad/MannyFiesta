grails.servlet.version = "2.5" // Change depending on target container compliance (2.5 or 3.0)
grails.project.class.dir = "target/classes"
grails.project.test.class.dir = "target/test-classes"
grails.project.test.reports.dir = "target/test-reports"
grails.project.target.level = 1.6
grails.project.source.level = 1.6
//logback.configurationFile = grails-app/conf/logback.xml
//grails.project.war.file = "target/${appName}-${appVersion}.war"

grails.project.dependency.resolution = {
    // inherit Grails' default dependencies
    inherits("global") {
        excludes "slf4j-log4j12", "slf4j-api", "log4j-1.2.16.jar"
        // uncomment to disable ehcache
        // excludes 'ehcache'
    }

    log "error" // log level of Ivy resolver, either 'error', 'warn', 'info', 'debug' or 'verbose'
    checksums true // Whether to verify checksums on resolve

    repositories {
        inherits true // Whether to inherit repository definitions from plugins
        grailsPlugins()
        grailsHome()
        grailsCentral()
        mavenCentral()

        // uncomment these to enable remote dependency resolution from public Maven repositories
        //mavenCentral()
        //mavenLocal()
        //mavenRepo "http://snapshots.repository.codehaus.org"
        //mavenRepo "http://repository.codehaus.org"
        //mavenRepo "http://download.java.net/maven/2/"
        //mavenRepo "http://repository.jboss.com/maven2/"
    }

    dependencies {
        // grails 1.3.7 ships with slf4j-api 1.5.8, so the latest version of logback you can use is 0.9.17
        build 'ch.qos.logback:logback-core:1.0.6',
                'ch.qos.logback:logback-classic:1.0.6',
                'org.slf4j:jcl-over-slf4j:1.6.6',
                'org.slf4j:jul-to-slf4j:1.6.6',
                'org.slf4j:slf4j-api:1.6.6',
                'ch.qos.logback:logback-access:1.0.6',
                'org.eclipse.osgi:org.eclipse.osgi:3.7.1.R37x_v20110808-1106',
                'org.eclipse.virgo.util:org.eclipse.virgo.util.parser.manifest:3.5.0.M04',
                'org.eclipse.virgo.util:org.eclipse.virgo.util.osgi.manifest:3.5.0.M04'

        runtime 'ch.qos.logback:logback-core:1.0.6',
                'ch.qos.logback:logback-classic:1.0.6',
                'ch.qos.logback:logback-access:1.0.6',
                'org.slf4j:jcl-over-slf4j:1.6.6',
                'org.slf4j:jul-to-slf4j:1.6.6',
                'org.slf4j:slf4j-api:1.6.6',
                'org.eclipse.osgi:org.eclipse.osgi:3.7.1.R37x_v20110808-1106',
                'org.eclipse.virgo.util:org.eclipse.virgo.util.parser.manifest:3.5.0.M04',
                'org.eclipse.virgo.util:org.eclipse.virgo.util.osgi.manifest:3.5.0.M04'

        // runtime 'mysql:mysql-connector-java:5.1.16'
    }

    plugins {
        runtime ":hibernate:$grailsVersion"
        runtime ":jquery:1.7.1"
        runtime ":resources:1.1.6"
        provided ":webxml:1.4.1"
        // Uncomment these (or add new ones) to enable additional resources capabilities
        //runtime ":zipped-resources:1.0"
        //runtime ":cached-resources:1.0"
        //runtime ":yui-minify-resources:0.1.4"

        build ":tomcat:$grailsVersion"
    }
}
