package example.model

import squants.Time
import cats.syntax.semigroup._
import example.model.shape.Shape
import example.model.shape.Shape.{Circle, Rectangle, center, collide, collidesWithBottom}
import monocle.macros.syntax.lens._

case class Entity(shape: Shape, center: Position, inertia: Inertia, speedOffset: Speed) {
  def animate(timeElapsed: Time): Entity = {
    val newInertia = inertia.next(timeElapsed)
    val displacement = (newInertia.lastSpeed |+| speedOffset).toDisplacement(timeElapsed)

    val newShape = shape match {
      case r: Rectangle => r.lens(_.bottomLeft).modify(_ + displacement)
      case c: Circle => c.lens(_.center).modify(_ + displacement)
    }
    copy(shape = newShape, inertia = newInertia, center = Shape.center(newShape)).collideWithBottom
  }

  private def collideWithBottom: Entity = if (collidesWithBottom(shape)) {
    val newShape = collide(shape)
    copy(inertia = inertia.copy(lastSpeed = Speed.none), shape = newShape, center = Shape.center(shape))
  } else this
}

object Entity {
  def apply(shape: Shape, inertia: Inertia, speedOffset: Speed): Entity = Entity(shape, center(shape), inertia, speedOffset)
}
