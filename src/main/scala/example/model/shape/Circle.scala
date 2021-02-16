package example.model.shape

import example.model.Position
import squants.space.Length
import monocle.macros.syntax.lens._
import squants.space.LengthConversions._

case class Circle(center: Position, radius: Length) {

  def bottom: Position = center.lens(_.value.y).modify(_ - radius)

  def collidesWithBottom: Boolean = bottom.value.y <= 0.meters

  def collide: Circle = if (collidesWithBottom) {
    val newCenter = center.lens(_.value.y).set(radius)
    copy(center = newCenter)
  } else this

}