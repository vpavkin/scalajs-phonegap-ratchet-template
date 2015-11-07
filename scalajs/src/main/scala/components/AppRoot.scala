package components

import cordova.EventManager
import japgolly.scalajs.react._
import japgolly.scalajs.react.vdom.all._
import org.scalajs.dom.Position
import store.{Subscriber, Store}

object AppRoot {

  case class State(coords: Option[Position])

  class Backend($: BackendScope[Unit, State]) {

    def render(state: State) =
      div(
        header(className := "bar bar-nav",
          h1(className := "title", "Scalajs + Phonegap, Bitch!")
        ),

        div(className := "content",
          state.coords.map(c =>
            div(
              p(className := "content-padded", s"Location: ${c.coords.latitude}, ${c.coords.longitude}")
            )
          ).getOrElse(p(className := "content-padded", s"No location detected"))
        )
      )
    def init = Callback {
      Store.subscribe(Subscriber("AppRoot", state => $.modState(_.copy(coords = state.coords)).runNow()))
    }
  }

  val component = ReactComponentB[Unit]("AppRoot")
    .initialState(State(None))
    .renderBackend[Backend]
    .componentDidMount($ =>
      $.backend.init.flatMap(_ => Callback {
        EventManager.watchPosition()
      }))
    .buildU
}
