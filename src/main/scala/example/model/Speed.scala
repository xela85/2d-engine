package example.model

import cats.kernel.Monoid
import squants.Time
import squants.motion.{MetersPerSecond, Velocity}

case class Speed(value: Vec2[Velocity]) {
  def toDisplacement(timeElapsed: Time): Position = Position(value.map(_ * timeElapsed))
}

object Speed {
  def none: Speed = Speed(Vec2(MetersPerSecond(0), MetersPerSecond(0)))

  implicit val monoiForSpeed: Monoid[Speed] = new Monoid[Speed] {
    override def empty: Speed = Speed.none

    override def combine(x: Speed, y: Speed): Speed = Speed(x.value + y.value)
  }
}