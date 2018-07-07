package org.gbz.sandbox

import utest.TestSuite
import wvlet.log.LogFormatter.SourceCodeLogFormatter
import wvlet.log.{LogSupport, Logger}

trait LoggableTest extends TestSuite with LogSupport {
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
