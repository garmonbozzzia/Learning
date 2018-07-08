package org.gbz.utils.log

import scala.language.experimental.macros

object Log {
  implicit final class Ext[A](val a: A) extends AnyVal {
    def log: A = macro LogMacros.logInfo
    def log(cause: Throwable): A = macro LogMacros.logInfoE
    def logC[B](msg: B): A = macro LogMacros.logInfoC
    def logC[B](msg: B, cause: Throwable): A = macro LogMacros.logInfoCE
    def logWith[B](reader: A => B): A = macro LogMacros.logInfoW
    def logWith[B](reader: A => B, cause: Throwable): A = macro LogMacros.logInfoWE

    def logInfo: A = macro LogMacros.logInfo
    def logInfo(cause: Throwable): A = macro LogMacros.logInfoE
    def logInfoC[B](msg: B): A = macro LogMacros.logInfoC
    def logInfoC[B](msg: B, cause: Throwable): A = macro LogMacros.logInfoCE
    def logInfoWith[B](reader: A => B): A = macro LogMacros.logInfoW
    def logInfoWith[B](reader: A => B, cause: Throwable): A = macro LogMacros.logInfoWE

    def logWarn: A = macro LogMacros.logWarn
    def logWarn(cause: Throwable): A = macro LogMacros.logWarnE
    def logWarnC[B](msg: B): A = macro LogMacros.logWarnC
    def logWarnC[B](msg: B, cause: Throwable): A = macro LogMacros.logWarnCE
    def logWarnWith[B](reader: A => B): A = macro LogMacros.logWarnW
    def logWarnWith[B](reader: A => B, cause: Throwable): A = macro LogMacros.logWarnWE

    def logError: A = macro LogMacros.logError
    def logError(cause: Throwable): A = macro LogMacros.logErrorE
    def logErrorC[B](msg: B): A = macro LogMacros.logErrorC
    def logErrorC[B](msg: B, cause: Throwable): A = macro LogMacros.logErrorCE
    def logErrorWith[B](reader: A => B): A = macro LogMacros.logErrorW
    def logErrorWith[B](reader: A => B, cause: Throwable): A = macro LogMacros.logErrorWE

    def trace: A = {println(a); a}
    def trace[B](b: B): A = {println(b); a}
    def traceWith[B](f: A => B): A = {println(f(a)); a}
  }
}
