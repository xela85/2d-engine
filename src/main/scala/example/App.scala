package example

import example.model.Model
import example.view.{View, ViewContext}
import org.scalajs.dom
import org.scalajs.dom.{document, window}
import org.scalajs.dom.html.Canvas
import org.scalajs.dom.raw.CanvasRenderingContext2D

object App {


  def main(args: Array[String]) = {
    val canvasElm = document.getElementById("game-canvas")
    val width = window.outerWidth
    val height = window.outerHeight
    canvasElm.setAttribute("style", s"width: $width; height: $height")
    val canvas = canvasElm.asInstanceOf[Canvas]
      .getContext("2d").asInstanceOf[CanvasRenderingContext2D]
    View.render(Model(200, Nil), ViewContext(canvas, width, height))
  }

}
