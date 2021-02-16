package example.controller

import example.event.Event
import example.model.AppliedForce.Ponctual
import example.model.{AppliedForce, ForceVector, Model, Speed, Vec2, Window}
import monocle.macros.syntax.lens._
import squants.motion.VelocityConversions._
import squants.motion.ForceConversions._
import squants.time.TimeConversions._

object Controller {

  def handleEvent(model: Model, event: Event): Model = event match {
    case Event.TimeElapsed(time) =>
      model.copy(player = model.player.animate(time))
    case Event.WindowResized(width, height) =>
      model.copy(window = Window(width, height))
    case Event.Jump if model.player.collidesWithBottom =>
      model.lens(_.player.inertia)
        .modify(_.addForce(AppliedForce(Ponctual(.2.seconds), ForceVector(Vec2(0.newtons, 3000.newtons)))))
    case Event.GoLeft =>
      model.lens(_.player.speedOffset.value.x)
        .set(-10.mps)
    case Event.GoRight =>
      model.lens(_.player.speedOffset.value.x)
        .set(10.mps)
    case Event.ResetMovement =>
      model.lens(_.player.speedOffset)
        .set(Speed(Vec2(0.mps, 0.mps)))
    case _ => model
  }


}

