package example.controller

import example.event.Event
import example.model.{Model, Window}

object Controller {

  def handleEvent(model: Model, event: Event): Model = event match {
    case Event.TimeElapsed(time) =>
      model.copy(rectangles = model.rectangles.map(_.animate(time)))
    case Event.WindowResized(width, height) =>
      model.copy(window = Window(width, height))
  }


}

