package example.model

import cats.kernel.Monoid
import example.model.shape.{Circle, Rectangle}
import squants.Time
import cats.syntax.semigroup._
import monocle.macros.syntax.lens._

case class Entity(shape: Entity.Shape, center: Position, inertia: Inertia, speedOffset: Speed) {
  def animate(timeElapsed: Time): Entity = {
    val newInertia = inertia.next(timeElapsed)
    val displacement = (newInertia.lastSpeed |+| speedOffset).toDisplacement(timeElapsed)

    val newShape = this.shape match {
      case Entity.RectangleShape(rectangle) => Entity.RectangleShape(rectangle.lens(_.bottomLeft).modify(_ + displacement))
      case Entity.CircleShape(circle) => Entity.CircleShape(circle.lens(_.center).modify(_ + displacement))
    }
    copy(shape = newShape, inertia = newInertia, center = center + displacement).collideWithBottom
  }

  def collidesWithBottom: Boolean = this.shape match {
    case Entity.RectangleShape(rectangle) => rectangle.collidesWithBottom
    case Entity.CircleShape(circle) => circle.collidesWithBottom
  }

  private def collideWithBottom: Entity = if (collidesWithBottom) {
    val newShape = this.shape match {
      case Entity.RectangleShape(rectangle) => Entity.RectangleShape(rectangle.collide)
      case Entity.CircleShape(circle) => Entity.CircleShape(circle.collide)
    }
    copy(inertia = inertia.copy(lastSpeed = Speed.none), shape = newShape)
  } else this
}

object Entity {

  sealed trait Shape

  case class RectangleShape(rectangle: Rectangle) extends Shape

  case class CircleShape(circle: Circle) extends Shape

  def rectangle(rectangle: Rectangle, inertia: Inertia, speedOffset: Speed) = Entity(RectangleShape(rectangle), rectangle.center, inertia, speedOffset)

  def circle(circle: Circle, inertia: Inertia, speedOffset: Speed) = Entity(CircleShape(circle), circle.center, inertia, speedOffset)

}
