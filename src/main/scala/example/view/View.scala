package example.view

import example.model.{Model, Rectangle}

object View {

  def render(model: Model, context: ViewContext): Unit = {
    context.canvas.clearRect(0, 0, model.window.width, model.window.height)
    if (model.maybeLast.forall(_.window != model.window)) {
      println("effective resize")
      context.canvasElm.width = model.window.width
      context.canvasElm.height = model.window.height
    }
    model.rectangles.foreach(render(model, _, context))
  }

  def render(model: Model, rectangle: Rectangle, context: ViewContext): Unit = {
    val topLeft = Pixel.fromVector(rectangle.topLeft, model.pixelsPerMeter)
    val size = Pixel.fromVector(rectangle.size, model.pixelsPerMeter)
    //println(rectangle)
    //println(s"top left: $topLeft")
    //println(s"bottom right: $size")
    context.canvas.fillRect(topLeft.x, model.window.height - topLeft.y, size.x, size.y)
  }

}
