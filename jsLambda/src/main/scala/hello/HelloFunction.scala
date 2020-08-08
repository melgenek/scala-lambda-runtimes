package hello

import scala.scalajs.js.annotation.JSExportTopLevel

object HelloFunction {
  @JSExportTopLevel("handle")
  def handle(): Unit = {
    println("Js tells that you are awesome.")
  }
}
