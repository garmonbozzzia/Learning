package org.gbz.utils.log

import utest.{*, TestSuite, Tests}
import wvlet.log.LogFormatter.SourceCodeLogFormatter
import wvlet.log.{LogSupport, Logger}

object LogTest extends TestSuite with LogSupport {
  import Log._
  Logger.setDefaultFormatter(SourceCodeLogFormatter)
  override def tests = Tests{
    * - {
      "Hello".log
      "Hello".log(new Exception("Exception!"))
      "Hello".log("Hello!")
      "Hello".logWith(x => x ++ x)
      "Hello".logWith(x => x ++ x, new ArithmeticException("d b z"))

      "Hello".logError
      "Hello".logError(new Exception("Exception!"))
      "Hello".logError("Hello!")
      "Hello".logErrorWith(x => x ++ x)
      "Hello".logErrorWith(x => x ++ x, new ArithmeticException("d b z"))

      "Hello".logWarn
      "Hello".logWarn(new Exception("Exception!"))
      "Hello".logWarn("Hello!")
      "Hello".logWarn("Hello!", new Exception("Exception!"))
      "Hello".logWarnWith(x => x ++ x)
      "Hello".logWarnWith(x => x ++ x, new ArithmeticException("d b z"))
    }
  }
}
