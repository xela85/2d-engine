package example.model.shape

import cats.kernel.Monoid
import example.model.{AppliedForce, Inertia, Speed, Vector}
import squants.mass.Mass
import squants.space.LengthConversions._

case class Rectangle(bottomLeft: Vector, size: Vector) {

  def topRight: Vector = bottomLeft + size

  def topLeft: Vector = bottomLeft + size.copy(x = 0.meters)

  def collidesWithBottom: Boolean = bottomLeft.y <= 0.meters

  def collide: Rectangle = if (collidesWithBottom) {
    val newBottomLeft = bottomLeft.copy(y = 0.meters)
    copy(
      bottomLeft = newBottomLeft
    )
  } else this

}

object Rectangle {

  def create(position: Vector, size: Vector): Rectangle = Rectangle(position, size)


}