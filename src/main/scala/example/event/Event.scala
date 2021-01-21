package example.event

import squants.time.{Milliseconds, Time}

sealed trait Event

object Event {

  //  case object KeyUp extends Event
  //  case object KeyDown extends Event

  case class TimeElapsed(value: Time) extends Event

}