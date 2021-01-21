package example.model

import cats.Monoid
import squants.Time
import squants.motion.MetersPerSecondSquared

case class Acceleration(kind: Acceleration.Kind, x: squants.motion.Acceleration, y: squants.motion.Acceleration) {
  def toSpeed(elapsedTime: Time): Speed = Speed(x * elapsedTime, y * elapsedTime)
}

object Acceleration {

  sealed trait Kind

  case object Ponctual extends Kind

  case object Constant extends Kind

  val gravity: Acceleration = Acceleration(Constant, MetersPerSecondSquared(0), MetersPerSecondSquared(9.81))

  implicit val monoidForAcceleration: Monoid[Acceleration] = new Monoid[Acceleration] {
    override def empty: Acceleration = Acceleration(Ponctual, MetersPerSecondSquared(0), MetersPerSecondSquared(0))
    override def combine(x: Acceleration, y: Acceleration): Acceleration =
      Acceleration(Ponctual, x.x + y.x, x.y + y.y)
  }

}
