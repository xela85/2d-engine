package example

import example.controller.Controller
import example.event.Event
import example.event.Event.{TimeElapsed, WindowResized}
import example.model.Model
import example.view.{View, ViewContext}
import org.scalajs.dom
import org.scalajs.dom.Window
import org.scalajs.dom.raw.{KeyboardEvent, UIEvent}
import squants.time.{Milliseconds, Time}

class JSGame(window: Window, var modelVar: Model, viewContext: ViewContext) {


  def animate(lastTimestamp: Double, currentTimestamp: Double): Unit = {
    val millisElapsed: Time = Milliseconds(currentTimestamp - lastTimestamp)
    modelVar = Controller.handleEvent(modelVar, TimeElapsed(millisElapsed))
    View.render(modelVar, viewContext)
    window.requestAnimationFrame(animate(currentTimestamp, _))
  }


  def resizeWindow(): Unit = {
    val event = WindowResized(window.innerWidth.toInt, window.innerHeight.toInt)
    println("resize window " + event)
    modelVar = Controller.handleEvent(modelVar, event)
  }


  def handleWindowResizeEvent(): Unit = {
    window.addEventListener("resize", (_: UIEvent) => resizeWindow())
  }

  def handleKeyEvents(): Unit = {
    window.addEventListener("keydown", pressKey)
    window.addEventListener("keyup", unpressKey)
  }

  private def pressKey(keyboardEvent: KeyboardEvent): Unit = keyboardEvent match {
    case keyboardEvent if keyboardEvent.keyCode  == 32 =>
      modelVar = Controller.handleEvent(modelVar, Event.Jump)
    case keyboardEvent if keyboardEvent.keyCode  == 37 =>
      modelVar = Controller.handleEvent(modelVar, Event.GoLeft)
    case keyboardEvent if keyboardEvent.keyCode  == 39 =>
      modelVar = Controller.handleEvent(modelVar, Event.GoRight)
    case _ =>
  }

  private def unpressKey(keyboardEvent: KeyboardEvent): Unit = keyboardEvent match {
    case keyboardEvent if keyboardEvent.keyCode  == 37 | keyboardEvent.keyCode  == 39 =>
      modelVar = Controller.handleEvent(modelVar, Event.ResetMovement)
    case _ =>
  }


}
