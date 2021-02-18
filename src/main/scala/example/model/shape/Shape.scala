package example.model.shape

import example.model.Position
import squants.space.Length
import monocle.macros.syntax.lens._
import squants.space.LengthConversions._

sealed trait Shape

object Shape {

  case class Circle(center: Position, radius: Length) extends Shape {
    def bottom: Position = center.lens(_.value.y).modify(_ - radius)
  }

  case class Rectangle(bottomLeft: Position, size: Position) extends Shape {
    def topRight: Position = bottomLeft + size

    def topLeft: Position = bottomLeft + size.lens(_.value.x).set(0.meters)
  }

  def collide(shape: Shape): Shape = shape match {
    case shape if !collidesWithBottom(shape) => shape
    case c: Circle =>
      val newCenter = c.center.lens(_.value.y).set(c.radius)
      c.copy(center = newCenter)
    case r: Rectangle =>
      val newBottomLeft = r.bottomLeft.lens(_.value.y).set(0.meters)
      r.copy(bottomLeft = newBottomLeft)
  }

  def collidesWithBottom(shape: Shape): Boolean = shape match {
    case c: Circle => c.bottom.value.y <= 0.meters
    case r: Rectangle => r.bottomLeft.value.y <= 0.meters
  }

  def center(shape: Shape): Position = shape match {
    case Circle(center, _) => center
    case Rectangle(bottomLeft, size) => Position((bottomLeft.value + size.value).map(_ / 2))
  }

}