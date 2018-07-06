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

object General extends CommonModule {
  override def ivyDeps = Agg(
    ivy"org.wvlet.airframe::airframe-log:0.50"
  )
}
