package example.view

import example.model.Model

object View {


  def render(model: Model, context: ViewContext): Unit = {
    context.canvas.clearRect(0, 0, context.width, context.height)
    context.canvas.moveTo(0, model.position)
    context.canvas.lineTo(200, model.position + 200)
    context.canvas.stroke()
  }

}
