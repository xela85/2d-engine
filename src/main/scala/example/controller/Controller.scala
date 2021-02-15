package example.controller

import example.event.Event
import example.model.AppliedForce.Ponctual
import example.model.{Acceleration, AppliedForce, ForceVector, Model, Speed, Window}
import monocle.macros.syntax.lens._
import squants.motion.VelocityConversions._
import squants.space.LengthConversions._
import squants.motion.ForceConversions._
import squants.time.TimeConversions._
import squants.Meters
import squants.motion.{Force, MetersPerSecond, MetersPerSecondSquared}

object Controller {

  def handleEvent(model: Model, event: Event): Model = event match {
    case Event.TimeElapsed(time) =>
      model.copy(player = model.player.animate(time))
    case Event.WindowResized(width, height) =>
      model.copy(window = Window(width, height))
    case Event.Jump if model.player.bottomLeft.y == 0.meters =>
      model.lens(_.player.inertia)
        .modify(_.addForce(AppliedForce(Ponctual(.2.seconds), ForceVector(0.newtons, 3000.newtons))))
    case Event.GoLeft =>
      model.lens(_.player.speedOffset.x)
        .set(-10.mps)
    case Event.GoRight =>
      model.lens(_.player.speedOffset.x)
        .set(10.mps)
    case Event.ResetMovement =>
      model.lens(_.player.speedOffset)
        .set(Speed(0.mps, 0.mps))
    case _ => model
  }


}

