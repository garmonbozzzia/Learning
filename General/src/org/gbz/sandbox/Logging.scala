package org.gbz.sandbox

import wvlet.log.LogFormatter._
import wvlet.log.{LogLevel, LogSupport, Logger}

/* Created on 06.07.18 */
import org.gbz.utils.log.Log._
object Logging extends LogSupport with App {
  
  def print(msg: String): Unit = {
    info(msg)
    debug("debug message")
    error("error")
    warn("Warning message")
  }

  Logger.setDefaultLogLevel(LogLevel.DEBUG)
  Logger.scheduleLogLevelScan
  Logger.stopScheduledLogLevelScan

  Logger.setDefaultFormatter(SourceCodeLogFormatter)
  "Hello".logInfo
  print("SourceCodeLogFormatter")
  Logger.setDefaultFormatter(AppLogFormatter)
  print("AppLogFormatter")
  Logger.setDefaultFormatter(TSVLogFormatter)
  print("TSVLogFormatter")
  Logger.setDefaultFormatter(IntelliJLogFormatter)
  print("IntelliJLogFormatter")
  Logger.setDefaultFormatter(SimpleLogFormatter)
  print("SimpleLogFormatter")
  Logger.setDefaultFormatter(BareFormatter)
  print("BareFormatter")
}
