package scala
package tools
package reflect

import scala.reflect.macros.runtime.Context

abstract class EnumerationImpl {
  val c: Context
  import c.universe._
  import treeInfo._

  def enumerationValue: Tree = {
    val name = c.enclosingVal match {
      case ValDef(_, name, _, _) => name.toString
      case _                     =>
        c.abort(c.enclosingPosition, "A call to Value should only be used as the definition of a val")
    }

    c.macroApplication match {
      case Applied(_, Nil, List(List(index))) =>
        q"this.Value($index, $name)"
      case Applied(_, Nil, List(Nil) | Nil) =>
        q"this.Value($name)"
      case _ =>
        c.abort(c.enclosingPosition, "Unexpected macro invocation shape")
    }

  }

}
