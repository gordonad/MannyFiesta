import ch.qos.logback.classic.encoder.PatternLayoutEncoder
import ch.qos.logback.core.ConsoleAppender
import ch.qos.logback.core.rolling.RollingFileAppender
import ch.qos.logback.core.rolling.TimeBasedRollingPolicy
import ch.qos.logback.core.status.OnConsoleStatusListener

statusListener(OnConsoleStatusListener)
appender("FILE", RollingFileAppender) {
  file = "${user.dir}/logs/app-access.log"
  rollingPolicy(TimeBasedRollingPolicy) {
    fileNamePattern = "${user.dir}/logs/app-access.%d{yyyy-MM-dd}.log.zip"
  }
  encoder(PatternLayoutEncoder) {
    pattern = "combined"
  }
}
appender("STDOUT", ConsoleAppender) {
  encoder(PatternLayoutEncoder) {
    pattern = "%n%n%fullRequest%n%n%fullResponse%n%n"
  }
}
appender("FILE2", RollingFileAppender) {
  file = "${user.dir}/logs/app-req-resp.log"
  rollingPolicy(TimeBasedRollingPolicy) {
    fileNamePattern = "${user.dir}/logs/app-req-resp.%d{yyyy-MM-dd}.log.zip"
  }
  encoder(PatternLayoutEncoder) {
    pattern = "%n%n%fullRequest%n%n%fullResponse%n%n"
  }
}