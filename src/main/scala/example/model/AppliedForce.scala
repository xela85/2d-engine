package example.model

import cats.Monoid
import example.model
import example.model.AppliedForce.{Constant, Kind, Ponctual}
import squants.Time
import squants.Force
import squants.mass.Mass
import squants.motion.AccelerationConversions._
import squants.motion.ForceConversions._
import squants.motion.MetersPerSecondSquared
import squants.time.TimeConversions._

case class AppliedForce(kind: Kind, vector: ForceVector) {

  def transform(elapsedTime: Time): Option[AppliedForce] = kind match {
    case Ponctual(duration) if duration - elapsedTime <= 0.seconds => None
    case Ponctual(duration) => Some(copy(kind = Ponctual(duration - elapsedTime)))
    case Constant => Some(this)
  }

}


object AppliedForce {

  sealed trait Kind

  case class Ponctual(duration: Time) extends Kind

  case object Constant extends Kind

  def gravity(mass: Mass): AppliedForce = AppliedForce(Constant, ForceVector(Vec2(0.newtons, -mass * 9.81.mpss)))


}