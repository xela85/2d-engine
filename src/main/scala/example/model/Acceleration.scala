package example.model

import cats.Monoid
import squants.Time
import squants.motion.AccelerationConversions._
import squants.motion.MetersPerSecondSquared

case class Acceleration(value: Vec2[squants.motion.Acceleration]) {
  def toSpeed(elapsedTime: Time): Speed = Speed(value.map(_ * elapsedTime))
}


