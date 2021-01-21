package example.controller

import example.event.Event
import example.model.Model

class Controller {

  def handleEvent(model: Model, event: Event): Model = event match {
    case Event.TimeElapsed(millis) =>
      val newRectangles = model.rectangles
        .map()

  }


}

