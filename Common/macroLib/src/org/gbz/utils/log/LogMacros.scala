package org.gbz.utils.log

import scala.reflect.macros._
import scala.language.experimental.macros


private[log] object LogMacros {

  private class MacroHelper[C <: blackbox.Context](val c: C) {

    import c.universe._

    val disabledLevels: Set[c.universe.Tree] = {
      val SettingsPrefix = "wvlet.log.disable."

      val TRACE: c.universe.Tree = q"wvlet.log.LogLevel.TRACE"
      val DEBUG: c.universe.Tree = q"wvlet.log.LogLevel.DEBUG"
      val INFO: c.universe.Tree  = q"wvlet.log.LogLevel.INFO"
      val WARN: c.universe.Tree  = q"wvlet.log.LogLevel.WARN"
      val ERROR: c.universe.Tree = q"wvlet.log.LogLevel.ERROR"

      c.settings
        .collect { case s if s startsWith SettingsPrefix => s stripPrefix SettingsPrefix }
        .collectFirst {
          case "ALL" | "ERROR" => Set(TRACE, DEBUG, INFO, WARN, ERROR)
          case "WARN"          => Set(TRACE, DEBUG, INFO, WARN)
          case "INFO"          => Set(TRACE, DEBUG, INFO)
          case "DEBUG"         => Set(TRACE, DEBUG)
          case "TRACE"         => Set(TRACE)
        }
        .getOrElse(Set.empty)
    }

    private def disabled(level: c.universe.Tree): Boolean = disabledLevels.exists(_.equalsStructure(level))

    def source = {
      val pos = c.enclosingPosition
      q"wvlet.log.LogSource(${pos.source.path}, ${pos.source.file.name}, ${pos.line}, ${pos.column})"
    }

    def log(level: c.universe.Tree, message: c.universe.Tree): c.universe.Tree = {
      val logger = q"this.logger"
      if (disabled(level)) q"()" else q"if ($logger.isEnabled($level)) $logger.log($level, $source, $message)"
    }

    def logWithCause(level: c.universe.Tree, message: c.universe.Tree, cause: c.universe.Tree): c.universe.Tree = {
      val logger = q"this.logger"
      if (disabled(level)) q"()"
      else q"if ($logger.isEnabled($level)) $logger.logWithCause($level, $source, $message, $cause)"
    }

    def logMethod(level: c.universe.Tree, message: c.universe.Tree): c.universe.Tree = {
      if (disabled(level)) q"()"
      else q"if (${c.prefix}.isEnabled($level)) ${c.prefix}.log($level, $source, $message)"
    }

    def logMethodWithCause(level: c.universe.Tree,
                           message: c.universe.Tree,
                           cause: c.universe.Tree): c.universe.Tree = {
      if (disabled(level)) q"()"
      else q"if (${c.prefix}.isEnabled($level)) ${c.prefix}.logWithCause($level, $source, $message, $cause)"
    }
  }

  def logInfo(c: blackbox.Context): c.Tree = {
    import c.universe._
    val q"$_($in)": c.universe.Tree = c.prefix.tree
    val log = new MacroHelper[c.type](c).log(q"wvlet.log.LogLevel.INFO", in)
    q"""
       $log
       $in
     """
  }
}
