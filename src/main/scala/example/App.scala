package example

import example.controller.Controller
import example.event.Event.TimeElapsed
import example.model.{Model, Rectangle}
import example.view.{View, ViewContext}
import org.scalajs.dom
import org.scalajs.dom.{document, window}
import org.scalajs.dom.html.Canvas
import org.scalajs.dom.raw.CanvasRenderingContext2D
import example.model.Vector
import squants.space.Meters
import squants.time.{Milliseconds, Time}

object App {


  def main(args: Array[String]): Unit = {
    val canvasElm = document.getElementById("game-canvas")
    val canvas = canvasElm.asInstanceOf[Canvas]
      .getContext("2d").asInstanceOf[CanvasRenderingContext2D]
    val rectangle = Rectangle.create(Vector(Meters(0), Meters(20)), Vector(Meters(3), Meters(3)))
    val startTime = window.performance.now()
    animate(Model(20, List(rectangle)), startTime, startTime)

    def animate(model: Model, lastTimestamp: Double, currentTimestamp: Double): Unit = {
      val millisElapsed: Time = Milliseconds(currentTimestamp - lastTimestamp)
      //      println(millisElapsed)
      val newModel = Controller.handleEvent(model, TimeElapsed(millisElapsed))
      //      println(model)
      val width = 1920
      val height = 700
      View.render(model, ViewContext(canvas, width, height))
      window.requestAnimationFrame(animate(newModel, currentTimestamp, _))
    }

  }


}
