package org.gbz.sandbox

import utest._
import wvlet.log.LogFormatter._
import wvlet.log.{LogLevel, LogSupport, Logger}

object AirframeLog extends TestSuite with LogSupport {
  Logger.setDefaultLogLevel(LogLevel.DEBUG)

  val tests = Tests {
    'airframeLog - {
      def print(): Unit = {
        debug("debug message")
        info("Hello")
        warn("Warning message")
        error("error")
      }

      'Formatters - {
        'Bare - print
        'SourceCodeLogFormatter - {
          Logger.setDefaultFormatter(SourceCodeLogFormatter)
          print
        }
        'AppLogFormatter - {
          Logger.setDefaultFormatter(AppLogFormatter)
          print
        }
        'TSVLogFormatter - {
          Logger.setDefaultFormatter(TSVLogFormatter)
          print
        }
        'IntelliJLogFormatter - {
          Logger.setDefaultFormatter(IntelliJLogFormatter)
          print
        }
        'SimpleLogFormatter - {
          Logger.setDefaultFormatter(SimpleLogFormatter)
          print
        }
        'BareFormatter - {
          Logger.setDefaultFormatter(BareFormatter)
          print
        }
      }

    }
  }

  override def utestBeforeEach(path: Seq[String]): Unit = {
    Logger.setDefaultFormatter(SourceCodeLogFormatter)
    Logger.scheduleLogLevelScan
    super.utestBeforeEach(path)
  }

  override def utestAfterEach(path: Seq[String]): Unit = {
    Logger.stopScheduledLogLevelScan
    super.utestAfterEach(path)
  }

  override def utestAfterAll(): Unit = {
    Logger.stopScheduledLogLevelScan
  }
}
