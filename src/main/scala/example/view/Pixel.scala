package example.view

import example.model.Position
import squants.space.Length

case class Pixel(x: Int, y: Int)

object Pixel {

  def fromPosition(position: Position, pixelsPerMeter: Int): Pixel =
    Pixel(fromLength(position.value.x, pixelsPerMeter), fromLength(position.value.y, pixelsPerMeter))

  def fromLength(length: Length, pixelsPerMeter: Int): Int = Math.round((length.toMeters * pixelsPerMeter).toInt)

}
