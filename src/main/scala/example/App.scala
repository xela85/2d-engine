package example

import cats.kernel.Monoid
import example.model.shape.Rectangle
import example.model.{AppliedForce, Entity, Inertia, Model, Speed, Vector, Window}
import example.view.ViewContext
import org.scalajs.dom.html.Canvas
import org.scalajs.dom.raw.{CanvasRenderingContext2D, HTMLCanvasElement}
import org.scalajs.dom.{document, window}
import squants.space.Meters
import squants.mass.MassConversions._
import squants.space.LengthConversions._

object App {


  def main(args: Array[String]): Unit = {
    val canvasElm = document.getElementById("game-canvas").asInstanceOf[HTMLCanvasElement]
    val canvas = canvasElm.getContext("2d").asInstanceOf[CanvasRenderingContext2D]
    val rectangle = Rectangle.create(Vector(0.meters, 20.meters), Vector(3.meters, 3.meters))
    val player = Entity.rectangle(rectangle, Inertia.mass(60.kilograms), Speed.none)
    val startTime = window.performance.now()
    val windowSize = Window(1920, 720)
    val model = Model(20, windowSize, player)

    val game = new JSGame(window, model, ViewContext(canvas, canvasElm))
    game.resizeWindow()
    game.handleWindowResizeEvent()
    game.handleKeyEvents()

    game.animate(startTime, startTime)
  }

}
