package example.model.shape

import cats.kernel.Monoid
import example.model.{AppliedForce, Inertia, Speed, Position}
import squants.mass.Mass
import squants.space.LengthConversions._
import monocle.macros.syntax.lens._

case class Rectangle(bottomLeft: Position, size: Position) {

  def topRight: Position = bottomLeft + size

  def topLeft: Position = bottomLeft + size.lens(_.value.x).set(0.meters)

  def collidesWithBottom: Boolean = bottomLeft.value.y <= 0.meters

  def collide: Rectangle = if (collidesWithBottom) {
    val newBottomLeft = bottomLeft.lens(_.value.y).set(0.meters)
    copy(
      bottomLeft = newBottomLeft
    )
  } else this

}

object Rectangle {

  def create(position: Position, size: Position): Rectangle = Rectangle(position, size)


}