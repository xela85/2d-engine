package example.model

import cats.kernel.Monoid
import squants.{Meters, Time}
import squants.space.Length

case class Position(value: Vec2[Length]) {

  def move(speed: Speed, elapsedTime: Time): Position = Position(value + speed.value.map(_ * elapsedTime))

  def -(v: Position): Position = Position(value - v.value)

  def +(v: Position): Position = Position(value + v.value)

}

object Position {
  implicit val monoidForPosition: Monoid[Position] = new Monoid[Position] {
    override def empty: Position = Position(Vec2(Meters(0), Meters(0)))

    override def combine(x: Position, y: Position): Position = x + y
  }
}