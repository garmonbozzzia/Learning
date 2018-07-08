package org.gbz.utils.log

import utest.{*, TestSuite, Tests}
import wvlet.log.LogFormatter.SourceCodeLogFormatter
import wvlet.log.{LogSupport, Logger}

object LogTest extends TestSuite with LogSupport {
  import Log._
  Logger.setDefaultFormatter(SourceCodeLogFormatter)
  override def tests = Tests{
    * - {
      "Hello".logInfo
      "Hello".logInfo(new Exception("Exception!"))
      "Hello".logInfoC("Hello!")
      "Hello".logInfoWith(x => x ++ x)
      "Hello".logInfoWith(x => x ++ x, new ArithmeticException("d b z"))

      "Hello".logError
      "Hello".logError(new Exception("Exception!"))
      "Hello".logErrorC("Hello!")
      "Hello".logErrorWith(x => x ++ x)
      "Hello".logErrorWith(x => x ++ x, new ArithmeticException("d b z"))

      "Hello".logWarn
      "Hello".logWarn(new Exception("Exception!"))
      "Hello".logWarnC("Hello!")
      "Hello".logWarnWith(x => x ++ x)
      "Hello".logWarnWith(x => x ++ x, new ArithmeticException("d b z"))
    }
  }
}
