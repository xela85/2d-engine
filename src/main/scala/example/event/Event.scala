package example.event

import squants.time.{Milliseconds, Time}

sealed trait Event

object Event {

  case class TimeElapsed(value: Time) extends Event
  case class WindowResized(width: Int, height: Int) extends Event
  case object Jump extends Event
  case object GoRight extends Event
  case object GoLeft extends Event
  case object ResetMovement extends Event

}