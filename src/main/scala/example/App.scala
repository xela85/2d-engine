package example

import example.model.{Model, Rectangle, Vector, Window}
import example.view.ViewContext
import org.scalajs.dom.html.Canvas
import org.scalajs.dom.raw.{CanvasRenderingContext2D, HTMLCanvasElement}
import org.scalajs.dom.{document, window}
import squants.space.Meters

object App {


  def main(args: Array[String]): Unit = {
    val canvasElm = document.getElementById("game-canvas").asInstanceOf[HTMLCanvasElement]
    val canvas = canvasElm.getContext("2d").asInstanceOf[CanvasRenderingContext2D]
    val rectangle = Rectangle.create(Vector(Meters(0), Meters(20)), Vector(Meters(3), Meters(3)))
    val startTime = window.performance.now()
    val windowSize = Window(1920, 720)
    val model = Model(20, windowSize, rectangle)

    val game = new JSGame(window, model, ViewContext(canvas, canvasElm))
    game.resizeWindow()
    game.handleWindowResizeEvent()
    game.handleKeyEvents()

    game.animate(startTime, startTime)
  }

}
