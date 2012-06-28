import ch.qos.logback.classic.encoder.PatternLayoutEncoder
import ch.qos.logback.core.ConsoleAppender
import ch.qos.logback.core.status.OnConsoleStatusListener

import static ch.qos.logback.classic.Level.*

scan "30 seconds"

statusListener OnConsoleStatusListener

appender("STDOUT", ConsoleAppender) {
    encoder(PatternLayoutEncoder) {
//        pattern = "%d{HH:mm:ss.SSS} [%thread] %-5level %logger{36} - %msg%n"
        pattern = "%.-1level|%-40.40logger{0}|%msg%n"
    }
}

logger "chapters.configuration", INFO
logger "org.springframework", WARN
logger "org.springframework.beans", DEBUG
// logger "org.springframework.transaction" ,WARN -->
// logger "org.springframework.orm" ,WARN -->
// logger "org.springframework.dao" ,DEBUG -->
logger "org.codehaus.groovy.grails.web.servlet", ERROR
logger "org.codehaus.groovy.grails.web.pages", ERROR
logger "org.codehaus.groovy.grails.web.sitemesh", ERROR
logger "org.codehaus.groovy.grails.web.mapping.filter", ERROR
logger "org.codehaus.groovy.grails.web.mapping", ERROR
logger "org.codehaus.groovy.grails.commons", ERROR
logger "org.codehaus.groovy.grails.plugins", ERROR
logger "org.codehaus.groovy.grails.orm.hibernate", ERROR
logger "org.springframework", ERROR
logger "org.hibernate", ERROR
logger "net.sf.ehcache.hibernate", ERROR


root DEBUG, ["STDOUT"]