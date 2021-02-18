package example

import cats.kernel.Monoid
import example.model.shape.{Circle, Rectangle}
import example.model.{AppliedForce, Entity, Inertia, Model, Position, Speed, Vec2, Window}
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
    val rectangle = Rectangle.create(Position(Vec2(0.meters, 20.meters)), Position(Vec2(3.meters, 3.meters)))
    val circle = Circle(Position(Vec2(20.meters, 20.meters)), 3.meters)
    val player = Entity.circle(circle, Inertia.mass(60.kilograms), Speed.none)
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
