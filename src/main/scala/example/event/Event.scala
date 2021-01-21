package example.event

sealed trait Event
object Event {
//  case object KeyUp extends Event
//  case object KeyDown extends Event

  case class TimeElapsed(millis: Long) extends Event

}