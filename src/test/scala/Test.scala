import example.controller.Controller
import example.event.Event.TimeElapsed
import example.model.{Model, Rectangle, Speed, Vector}
import org.scalatest.wordspec.AnyWordSpec
import org.scalatest.matchers.should.Matchers
import squants.motion.MetersPerSecond
import squants.{Meters, Seconds}

class Test extends AnyWordSpec with Matchers {


  "dazd" in {
    val rectangle = Rectangle.create(Vector(Meters(0), Meters(0)), Vector(Meters(3), Meters(3)))
    val model = Model(20, List(rectangle))
    val updatedModel = Controller.handleEvent(model, TimeElapsed(Seconds(1)))
    updatedModel.rectangles.head.inertia.speed shouldBe Speed(MetersPerSecond(0), MetersPerSecond(9.81))
    updatedModel.rectangles.head.position shouldBe Vector(Meters(0), Meters(19.62))

  }

}
