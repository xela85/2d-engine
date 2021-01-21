package example.model

import cats.kernel.Monoid
import squants.Time
import cats.syntax.monoid._


case class Rectangle(position: Vector, size: Vector, inertia: Inertia, speedOffset: Speed) {

  def animate(timeElapsed: Time): Rectangle = {
    val newInertia = inertia.next(timeElapsed)
    val displacement = (newInertia.speed |+| speedOffset).toDisplacement(timeElapsed)
    Rectangle(position |+| displacement, size, newInertia, speedOffset)
  }

}

object Rectangle {

  def create(position: Vector, size: Vector): Rectangle = Rectangle(position, size, Inertia(Monoid.empty[Speed], List(Acceleration.gravity)), Monoid.empty[Speed])


}