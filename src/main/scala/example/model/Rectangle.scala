package example.model

import cats.kernel.Monoid
import squants.{Meters, Time}
import cats.syntax.monoid._
import squants.mass.Mass
import squants.space.LengthConversions._

case class Rectangle(bottomLeft: Vector, size: Vector, inertia: Inertia, speedOffset: Speed) {

  def animate(timeElapsed: Time): Rectangle = {
    val newInertia = inertia.next(timeElapsed)
    val displacement = (newInertia.lastSpeed |+| speedOffset).toDisplacement(timeElapsed)
    Rectangle(bottomLeft + displacement, size, newInertia, speedOffset).collideWithBottom
  }

  def topRight: Vector = bottomLeft + size

  def topLeft: Vector = bottomLeft + size.copy(x = 0.meters)

  def collideWithBottom: Rectangle = if (bottomLeft.y < 0.meters) {
    val newBottomLeft = bottomLeft.copy(y = 0.meters)
    copy(
      bottomLeft = newBottomLeft,
      inertia = inertia.copy(lastSpeed = Monoid.empty[Speed])
    )
  } else this

}

object Rectangle {

  def create(position: Vector, size: Vector, mass: Mass): Rectangle = Rectangle(position, size, Inertia(Monoid.empty[Speed], mass, List(AppliedForce.gravity(mass))), Monoid.empty[Speed])


}