package example.model

import cats.kernel.Monoid
import cats.syntax.foldable._
import cats.syntax.monoid._
import example.model.Acceleration.Constant
import squants.Time

case class Inertia(speed: Speed, accelerations: List[Acceleration]) {

  def next(elapsedTime: Time): Inertia = {
    val newSpeed = speed |+| accelerations.foldMap(_.toSpeed(elapsedTime))
    Inertia(newSpeed, accelerations = accelerations.filter(_.kind == Constant))
  }

  def addAcceleration(acceleration: Acceleration): Inertia =
    copy(speed = speed, accelerations = acceleration :: accelerations)

}

object Inertia {
  def empty: Inertia = Inertia(Monoid.empty[Speed], Nil)
}