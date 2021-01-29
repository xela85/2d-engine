package example.model

import cats.kernel.Monoid
import squants.{Meters, Time}
import squants.space.Length

case class Vector(x: Length, y: Length) {

  def move(speed: Speed, elapsedTime: Time): Vector = {
    Vector(
      x + speed.x * elapsedTime,
      y + speed.y * elapsedTime
    )
  }

  def -(v: Vector): Vector = Vector(x - v.x, y - v.y)
  def +(v: Vector): Vector = Vector(x + v.x, y + v.y)

}

object Vector {
  implicit val monoidForPosition: Monoid[Vector] = new Monoid[Vector] {
    override def empty: Vector = Vector(Meters(0), Meters(0))

    override def combine(x: Vector, y: Vector): Vector = x + y
  }
}