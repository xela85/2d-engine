package example.model

import cats.kernel.Monoid
import cats.syntax.foldable._
import cats.syntax.monoid._
import example.model.AppliedForce.Constant
import squants.Time
import squants.mass.Mass

case class Inertia(lastSpeed: Speed, mass: Mass, forces: List[AppliedForce]) {

  def next(elapsedTime: Time): Inertia = {
    val newSpeed = lastSpeed |+| acceleration.toSpeed(elapsedTime)
    Inertia(newSpeed, mass, forces = forces.flatMap(_.transform(elapsedTime)))
  }

  def addForce(force: AppliedForce): Inertia =
    copy(forces = force :: forces)

  def acceleration: Acceleration = forces.map(_.vector).combineAll.toAcceleration(mass)

}