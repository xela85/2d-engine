package example.view

import example.model.shape.Shape.{Circle, Rectangle}
import example.model.{Entity, Model}

object View {

  def render(model: Model, context: ViewContext): Unit = {
    context.canvas.clearRect(0, 0, model.window.width, model.window.height)
    context.canvasElm.width = model.window.width
    context.canvasElm.height = model.window.height
    render(model, model.player, context)
  }

  def render(model: Model, shape: Entity, context: ViewContext): Unit = {
    renderComments(
      List(
        model.player.inertia.toString,
        shape.toString,
      ),
      context
    )

    shape.shape match {
      case rectangle: Rectangle =>
        val topLeft = Pixel.fromPosition(rectangle.topLeft, model.pixelsPerMeter)
        val size = Pixel.fromPosition(rectangle.size, model.pixelsPerMeter)
        context.canvas.fillRect(topLeft.x, model.window.height - topLeft.y, size.x, size.y)
      case circle: Circle =>
        val center = Pixel.fromPosition(circle.center, model.pixelsPerMeter)
        context.canvas.arc(center.x, model.window.height - center.y, Pixel.fromLength(circle.radius, model.pixelsPerMeter), 0, 2 * Math.PI)
        context.canvas.fill()
    }


  }


  private def renderComments(comments: List[String], context: ViewContext): Unit =
    comments.zipWithIndex.foreach { case (comment, index) =>
      context.canvas.fillText(comment, 10, 20 + index * 20)
    }

}
