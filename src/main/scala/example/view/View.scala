package example.view

import example.model.{Model, Rectangle}

object View {


  def render(model: Model, context: ViewContext): Unit = {
    context.canvas.clearRect(0, 0, context.width, context.height)
    model.rectangles.foreach(render(_, model.pixelsPerMeter, context))
  }

  def render(rectangle: Rectangle, pixelsPerMeter: Int, context: ViewContext): Unit = {
    val position = Pixel.fromVector(rectangle.position, pixelsPerMeter)
    val size = Pixel.fromVector(rectangle.size, pixelsPerMeter)
    context.canvas.fillRect(position.x, position.y, size.x, size.y)
  }

}
