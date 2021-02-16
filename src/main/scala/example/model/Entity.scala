package example.model

import cats.kernel.Monoid
import example.model.shape.{Circle, Rectangle}
import squants.Time
import cats.syntax.semigroup._

case class Entity(shape: Entity.Shape, inertia: Inertia, speedOffset: Speed) {
  def animate(timeElapsed: Time): Entity = {
    val newInertia = inertia.next(timeElapsed)
    val displacement = (newInertia.lastSpeed |+| speedOffset).toDisplacement(timeElapsed)

    val kind = this.shape match {
      case Entity.RectangleShape(rectangleShape) => Entity.RectangleShape(Rectangle(rectangleShape.bottomLeft + displacement, rectangleShape.size))
      case Entity.CircleShape(rectangleShape) => ???
    }
    Entity(kind, newInertia, speedOffset).collideWithBottom
  }

  def collidesWithBottom: Boolean = this.shape match {
    case Entity.RectangleShape(rectangle) => rectangle.collidesWithBottom
    case Entity.CircleShape(circle) => false
  }

  private def collideWithBottom: Entity = if(collidesWithBottom) {
    val newShape = this.shape match {
      case Entity.RectangleShape(rectangleShape) => Entity.RectangleShape(rectangleShape.collide)
      case Entity.CircleShape(circleShape) => ???
    }
    copy(inertia = inertia.copy(lastSpeed = Speed.none), shape = newShape)
  } else this
}

object Entity {

  sealed trait Shape

  case class RectangleShape(rectangle: Rectangle) extends Shape

  case class CircleShape(circle: Circle) extends Shape

  def rectangle(rectangle: Rectangle, inertia: Inertia, speedOffset: Speed) = Entity(RectangleShape(rectangle), inertia, speedOffset)

  def circle(circle: Circle, inertia: Inertia, speedOffset: Speed) = Entity(CircleShape(circle), inertia, speedOffset)

}
