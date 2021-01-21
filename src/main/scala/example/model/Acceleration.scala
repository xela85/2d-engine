package example.model

import squants.motion.MetersPerSecondSquared

case class Acceleration(kind: Acceleration.Kind, value: squants.motion.Acceleration)

object Acceleration {

  sealed trait Kind

  case object Ponctual extends Kind

  case object Constant extends Kind

  val gravity: Acceleration = Acceleration(Constant, MetersPerSecondSquared(9.81))

}
