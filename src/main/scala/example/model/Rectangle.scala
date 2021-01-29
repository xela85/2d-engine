package example.model

import cats.kernel.Monoid
import squants.{Meters, Time}
import cats.syntax.monoid._
import squants.motion.MetersPerSecond


case class Rectangle(bottomLeft: Vector, size: Vector, inertia: Inertia, speedOffset: Speed) {

  def animate(timeElapsed: Time): Rectangle = {
    val newInertia = inertia.next(timeElapsed)
    val displacement = (newInertia.speed |+| speedOffset).toDisplacement(timeElapsed)
    Rectangle(bottomLeft + displacement, size, newInertia, speedOffset).collideWithBottom
  }

  def topRight: Vector = bottomLeft + size

  def topLeft: Vector = bottomLeft + size.copy(x = Meters(0))

  def collideWithBottom: Rectangle = if (bottomLeft.y < Meters(0)) {
    val newBottomLeft = bottomLeft.copy(y = Meters(0))
    copy(
      bottomLeft = newBottomLeft,
      inertia = inertia.copy(speed = Monoid.empty[Speed])
    )
  } else this

}

object Rectangle {

  def create(position: Vector, size: Vector): Rectangle = Rectangle(position, size, Inertia(Monoid.empty[Speed], List(Acceleration.gravity)), Monoid.empty[Speed])


}