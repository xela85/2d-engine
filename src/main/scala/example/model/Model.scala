package example.model

case class Model(pixelsPerMeter: Int, window: Window, rectangles: List[Rectangle], maybeLast: Option[Model])
