package example.model

import cats.Monoid
import squants.Time
import squants.motion.AccelerationConversions._
import squants.motion.MetersPerSecondSquared

case class Acceleration(x: squants.motion.Acceleration, y: squants.motion.Acceleration) {
  def toSpeed(elapsedTime: Time): Speed = Speed(x * elapsedTime, y * elapsedTime)
}


