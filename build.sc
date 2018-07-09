import coursier.maven.MavenRepository
import mill._
import mill.define.Target
import mill.scalalib._
import mill.util.Loose

trait CommonModule extends ScalaModule {
  def scalaVersion = "2.12.4"

  override def repositories =
    super.repositories ++ Seq(MavenRepository("https://dl.bintray.com/cakesolutions/maven/"))

  object test extends Tests {
    override def repositories = {
      super.repositories ++ Seq(MavenRepository("https://dl.bintray.com/cakesolutions/maven/"))
    }

    override def ivyDeps: Target[Loose.Agg[Dep]] = Agg(
      ivy"com.lihaoyi::utest:0.6.4",
      ivy"com.lihaoyi::ammonite-ops:1.1.0")

    def testFrameworks = Seq("utest.runner.Framework")
  }

}

trait MacroModule extends ScalaModule {
  def scalaVersion = "2.12.4"
  override def ivyDeps = Agg(
    ivy"org.scala-lang:scala-reflect:2.12.4",
  )
}

object Common extends CommonModule {
  override def ivyDeps = Agg(
    ivy"org.wvlet.airframe::airframe-log:0.50"
  )

  object macroLib extends MacroModule

  override def moduleDeps = Seq(macroLib)
}

object General extends CommonModule {

  object macroLib extends MacroModule

  override def ivyDeps = Agg(
    ivy"org.wvlet.airframe::airframe-log:0.50"
  )

  override def moduleDeps = Seq(macroLib, Common)
}
