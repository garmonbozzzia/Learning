package org.gbz.utils.log

import scala.language.experimental.macros

object Log {
  implicit final class Ext[A](val obj: A) extends AnyVal {
    def logInfo: A = macro LogMacros.logInfo
  }
}
