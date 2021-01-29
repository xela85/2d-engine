package example.view

import example.model.{Model, Rectangle}

object View {


  def render(model: Model, context: ViewContext): Unit = {
    context.canvas.clearRect(0, 0, context.width, context.height)
    model.rectangles.foreach(render(_, model.pixelsPerMeter, context))
  }

  def render(rectangle: Rectangle, pixelsPerMeter: Int, context: ViewContext): Unit = {
    val topLeft = Pixel.fromVector(rectangle.topLeft, pixelsPerMeter)
    val size = Pixel.fromVector(rectangle.size, pixelsPerMeter)
    //println(rectangle)
    //println(s"top left: $topLeft")
    //println(s"bottom right: $size")
    context.canvas.fillRect(topLeft.x, context.height - topLeft.y, size.x, size.y)
  }

}
