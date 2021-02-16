package example.model

import cats.Monoid
import squants.Force
import squants.mass.Mass
import squants.motion.ForceConversions._

case class ForceVector(value: Vec2[Force]) {
  def toAcceleration(mass: Mass) = Acceleration(value.map(_ / mass))
}

object ForceVector {
  implicit val monoidForForceVector: Monoid[ForceVector] = new Monoid[ForceVector] {
    override def empty: ForceVector = ForceVector(Vec2(0.newtons, 0.newtons))

    override def combine(x: ForceVector, y: ForceVector): ForceVector =
      ForceVector(x.value + y.value)
  }
}