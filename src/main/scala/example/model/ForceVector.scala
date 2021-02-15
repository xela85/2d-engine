package example.model

import cats.Monoid
import squants.Force
import squants.mass.Mass
import squants.motion.ForceConversions._

case class ForceVector(x: Force, y: Force) {
  def toAcceleration(mass: Mass) = Acceleration(x / mass, y / mass)
}

object ForceVector {
  implicit val monoidForForceVector: Monoid[ForceVector] = new Monoid[ForceVector] {
    override def empty: ForceVector = ForceVector(0.newtons, 0.newtons)

    override def combine(x: ForceVector, y: ForceVector): ForceVector =
      ForceVector(x.x + y.x, x.y + y.y)
  }
}