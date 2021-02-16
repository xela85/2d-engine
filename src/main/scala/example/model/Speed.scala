package example.model

import cats.kernel.Monoid
import squants.Time
import squants.motion.{MetersPerSecond, Velocity}

case class Speed(x: Velocity, y: Velocity) {
  def toDisplacement(timeElapsed: Time): Vector = Vector(x * timeElapsed, y * timeElapsed)
}

object Speed {
  def none: Speed = Speed(MetersPerSecond(0), MetersPerSecond(0))

  implicit val monoiForSpeed: Monoid[Speed] = new Monoid[Speed] {
    override def empty: Speed = Speed.none

    override def combine(x: Speed, y: Speed): Speed = Speed(x.x + y.x, x.y + y.y)
  }
}