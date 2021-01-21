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
import squants.Seconds

import scala.concurrent.duration._
import squants.space.Meters
import squants.time.{Milliseconds, Time}

import scala.scalajs.js.Date
import scala.scalajs.js.timers.{setInterval, setTimeout}

object App {


  def main(args: Array[String]): Unit = {
    val canvasElm = document.getElementById("game-canvas")
    //    canvasElm.setAttribute("style", s"width: $width; height: $height")
    val canvas = canvasElm.asInstanceOf[Canvas]
      .getContext("2d").asInstanceOf[CanvasRenderingContext2D]
    val rectangle = Rectangle.create(Vector(Meters(0), Meters(0)), Vector(Meters(3), Meters(3)))


    animate(Model(20, List(rectangle)), Date.now())


    def animate(model: Model, lastDate: Double): Unit = {
      val newDate = Date.now()
      val millisElapsed: Time = Milliseconds(newDate - lastDate)
      println(millisElapsed)
      val newModel = Controller.handleEvent(model, TimeElapsed(millisElapsed))
      println(model)
      View.render(model, ViewContext(canvas, canvasElm.clientWidth, canvasElm.clientHeight))
      window.requestAnimationFrame(_ => animate(newModel, newDate))
    }

  }

  private def animate(model: Model) = {

  }


}
