package example.controller

import example.event.Event
import example.model.Model

object Controller {

  def handleEvent(model: Model, event: Event): Model = event match {
    case Event.TimeElapsed(time) =>
      model.copy(rectangles = model.rectangles.map(_.animate(time)))

  }


}

