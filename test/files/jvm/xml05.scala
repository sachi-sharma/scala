import scala.tools.partest.ReplTest

// disabled; see comment in jvm/interpreter.scala
object Test extends App {
  def code = """
<city name="San Jos&eacute;"/>
  """

  println(
    """
      |scala> <city name="San Jos&eacute;"/>
      |res0: scala.xml.Elem = <city name="San Jos&eacute;"/>
      |
      |scala> :quit""".stripMargin)
}