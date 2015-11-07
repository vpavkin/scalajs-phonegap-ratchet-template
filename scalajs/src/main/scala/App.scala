import components.AppRoot
import japgolly.scalajs.react._
import org.scalajs.dom.document

import scala.scalajs.js.annotation.JSExport

@JSExport
object App {

  @JSExport
  def start() =
    ReactDOM.render(AppRoot.component(), document.getElementById("root"))
}
