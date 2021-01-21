package example.view

import example.model.Vector

case class Pixel(x: Int, y: Int)

object Pixel {

  def fromVector(vector: Vector, pixelsPerMeter: Int): Pixel = Pixel(Math.round(vector.x.toMeters.toInt * pixelsPerMeter), vector.y.toMeters.toInt * pixelsPerMeter)

}
