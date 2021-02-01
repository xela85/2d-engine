package example.view

import org.scalajs.dom.html.Canvas
import org.scalajs.dom.raw.{CanvasRenderingContext2D, HTMLCanvasElement}

case class ViewContext(canvas: CanvasRenderingContext2D, canvasElm: HTMLCanvasElement)
