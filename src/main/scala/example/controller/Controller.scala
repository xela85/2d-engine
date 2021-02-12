package example.controller

import example.event.Event
import example.model.Acceleration.{Constant, Ponctual}
import example.model.{Acceleration, Model, Speed, Window}
import monocle.macros.syntax.lens._
import squants.Meters
import squants.motion.{MetersPerSecond, MetersPerSecondSquared}

object Controller {

  def handleEvent(model: Model, event: Event): Model = event match {
    case Event.TimeElapsed(time) =>
      model.copy(player = model.player.animate(time))
    case Event.WindowResized(width, height) =>
      model.copy(window = Window(width, height))
    case Event.Jump if model.player.bottomLeft.y == Meters(0) =>
      model.lens(_.player.inertia)
        .modify(_.addAcceleration(Acceleration(Ponctual, MetersPerSecondSquared(0), MetersPerSecondSquared(600))))
    case Event.GoLeft =>
      model.lens(_.player.speedOffset.x)
        .set(MetersPerSecond(-10))
    case Event.GoRight =>
      model.lens(_.player.speedOffset.x)
        .set(MetersPerSecond(10))
    case Event.ResetMovement =>
      model.lens(_.player.speedOffset)
        .set(Speed(MetersPerSecond(0), MetersPerSecond(0)))
    case _ => model
  }


}

