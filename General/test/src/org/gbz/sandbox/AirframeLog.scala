package org.gbz.sandbox

import utest._
import wvlet.log.LogFormatter._
import wvlet.log.Logger

object AirframeLog extends LoggableTest {
  val tests = Tests {
    'AirframeLog - {
      def print(): Unit = {
        debug("debug message")
        info("Hello")
        warn("Warning message")
        error("error")
      }

      'Formatters - {
        'Bare - print()
        'SourceCodeLogFormatter - {
          Logger.setDefaultFormatter(SourceCodeLogFormatter)
          print()
        }
        'AppLogFormatter - {
          Logger.setDefaultFormatter(AppLogFormatter)
          print()
        }
        'TSVLogFormatter - {
          Logger.setDefaultFormatter(TSVLogFormatter)
          print()
        }
        'IntelliJLogFormatter - {
          Logger.setDefaultFormatter(IntelliJLogFormatter)
          print()
        }
        'SimpleLogFormatter - {
          Logger.setDefaultFormatter(SimpleLogFormatter)
          print()
        }
        'BareFormatter - {
          Logger.setDefaultFormatter(BareFormatter)
          print()
        }
      }
    }
  }
}
