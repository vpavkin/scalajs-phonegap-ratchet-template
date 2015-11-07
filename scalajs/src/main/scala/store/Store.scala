package store

import org.scalajs.dom.Position

case class ApplicationState(coords: Option[Position])
case class Subscriber(name: String, callback: ApplicationState => Unit)

object Store {

  private var _state: ApplicationState = new ApplicationState(
    None
  )
  private var _subscribers: Map[String, Subscriber] = Map.empty

  def subscribe(s: Subscriber) =
    _subscribers = _subscribers + (s.name -> s)

  def unsubscribe(s: Subscriber) =
    _subscribers = _subscribers - s.name

  private def dispatch = _subscribers.values.foreach(_.callback(_state))

  private def modState(newState: ApplicationState) = {
    _state = newState
    dispatch
  }

  def coords = _state.coords
  def setCoords(coords: Option[Position]) = modState(_state.copy(coords = coords))
}
