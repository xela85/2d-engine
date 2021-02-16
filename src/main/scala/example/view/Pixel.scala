package example.view

import example.model.Position

case class Pixel(x: Int, y: Int)

object Pixel {

  def fromVector(position: Position, pixelsPerMeter: Int): Pixel =
    Pixel(Math.round((position.value.x.toMeters * pixelsPerMeter).toInt),
      (position.value.y.toMeters * pixelsPerMeter).toInt)

}
