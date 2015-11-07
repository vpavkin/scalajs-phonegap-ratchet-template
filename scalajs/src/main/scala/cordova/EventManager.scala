package cordova

import japgolly.scalajs.react.Callback
import org.scalajs.dom.raw.{PositionOptions, PositionError, Position}
import org.scalajs.dom.{navigator, document}
import store.Store

import scala.scalajs.js

object EventManager {

  def nav = org.scalajs.dom.navigator.asInstanceOf[js.Dynamic]

  def alert[T <: js.Any](s: T) = nav.notification.alert.apply(s, null, null, null)

  def watchPosition() = {
    Callback.log("registering").runNow()
    document.addEventListener("deviceready", (_: Any) => {
      Callback.log("READY").runNow()
      Callback.log(nav.notification).runNow()
      navigator.geolocation.watchPosition(
        (p: Position) => {
          Callback.log("received pos: ", p).runNow()
          Store.setCoords(Some(p))
        },
        (p: PositionError) => {
          Callback.log("received pos error: ", p).runNow()
        },
        js.Dynamic.literal("enableHighAccuracy" -> true, "timeout" -> 10000).asInstanceOf[PositionOptions]
      )
    }, false)
  }

}
