package scala

import org.junit.runner.RunWith
import org.junit.Assert._
import org.junit.Test
import org.junit.runners.JUnit4

import scala.tools.testing.RunTesting

@RunWith(classOf[JUnit4])
class EnumerationTest extends RunTesting {
  import runner._

  @Test
  def `enumeration value names are unaffected by aliases (5462)`() = {
    val code = """
      object Xab extends Enumeration {
        type Xab = Value
        val Dab = Bab
        val Aab, Bab = Value
        val Cab = Aab
      }

      object Yba extends Enumeration {
        type Yba = Value
        val Xba = Uba
        val Tba = Value(123)
        val Uba = Value(321)
        val Dba = Tba
      }
      (Xab.values.toList.map(_.toString), Yba.values.toList.map(_.toString))
    """

    assertEquals((List("Aab", "Bab"), List("Tba", "Uba")), run[(List[String], List[String])](code))
  }

}
