package thirdstyle

/* Note: such code style is unusual in Scala. It's only for
 * illustrative purposes, to fit the whole app in one file.
 * Usually you mimic Java conventions, separating tests
 * from production code.
 */
import org.specs2.mutable.Specification
import org.specs2.mock.Mockito
import scala.util.Random.{
  nextInt => randomInt,
  nextLong => randomLong
}
import java.util.Date

/* ________________________________________________________ *
 *           The Bootstrap module                           *
 * -------------------------------------------------------- */
package bootstrap {
  // Depends on every other module
  import exterior._
  import centrum._
  import interior._

  /** Main class */
  object Application {

    def main (args: Array[String]): Unit = {
      val factory = new Factory()
      for (i <- 1 to 3) {
        val id = randomInt()
        val date = new Date()
        val lorem = factory.createLorem(id, date)
        lorem.process()
      }
    }
  }

  /** Factory responsible for wiring up objects */
  class Factory {

    /** creates lorem and all its dependencies
      * @param id The id required by Amet
      * @param date The date required by Amet */
    def createLorem(id: Int, date: Date) = {
      var size: Long = 0
      val amet = new Amet(id, date, () => size)
      val sit = new Sit(amet)
      val dolor = new Dolor(sit)
      val ipsum = new Ipsum(dolor, (newSize: Long) => size = newSize)
      new Lorem(ipsum)
    }
  }
}

/* ________________________________________________________ *
 *         The Exterior module                              *
 * -------------------------------------------------------- */
package exterior {
  import supercentrum._

  /** Lorem component
    * @param ipsum The SuperIpsum that continues processing
    */
  class Lorem(ipsum: SuperIpsum) {

    /** Does Lorem processing */
    def process() {
      // ... skipped fragment
      ipsum.process()
    }
  }

  class LoremSpec extends Specification with Mockito {

    "a lorem" should {
      "invoke method of ipsum" in {
        // given
        val ipsum = mock[SuperIpsum]
        val lorem = new Lorem(ipsum)
        // when
        lorem.process()
        // then
        there was one(ipsum).process()
      }
    }
  }
}

/* ________________________________________________________ *
 *                 The SuperCentrum module                  *
 * -------------------------------------------------------- */
package supercentrum {

  /** Ipsum component */
  trait SuperIpsum {

    /** Does Ipsum processing */
    def process()
  }
}
/* ________________________________________________________ *
 *            The Centrum module                            *
 * -------------------------------------------------------- */
package centrum {
  import supercentrum._
  import superinterior._

  /** Ipsum component implementation
    * @param dolor The Dolor that continues processing
    * @param setSize The function for setting the size to be used later
    */
  class Ipsum(dolor: Dolor, setSize: Long => Unit) extends SuperIpsum {

    /** Does Ipsum processing */
    def process() {
      // ... skipped fragment
      setSize(randomLong())
      dolor.process()
    }
  }

  /** Dolor component
    * @param sit The SuperSit that continues processing
    */
  class Dolor(sit: SuperSit) {

    /** Does Dolor processing */
    def process() {
      // ... skipped fragment
      sit.process()
    }
  }

  class IpsumSpec extends Specification with Mockito {

    "a ipsum" should {
      "invoke method of dolor" in {
        // given
        val dolor = mock[Dolor]
        val setSize = mock[Long => Unit]
        val ipsum = new Ipsum(dolor, setSize)
        // when
        ipsum.process()
        // then
        there was one(setSize).apply(anyLong)
        there was one(dolor).process()
      }
    }
  }

  class DolorSpec extends Specification with Mockito {

    "a dolor" should {
      "invoke method of sit" in {
        // given
        val sit = mock[SuperSit]
        val dolor = new Dolor(sit)
        // when
        dolor.process()
        // then
        there was one(sit).process()
      }
    }
  }
}
/* ________________________________________________________ *
 *         The SuperInterior module                         *
 * -------------------------------------------------------- */
package superinterior {

  /** Sit component */
  trait SuperSit {

    /** Does Sit processing */
    def process()
  }
}
/* ________________________________________________________ *
 *         The Interior module                              *
 * -------------------------------------------------------- */
package interior {
  import superinterior._

  /** Sit component implementation
    * @param amet The Amet that continues processing
    */
  class Sit(amet: Amet) extends SuperSit {

    /** Does Sit processing */
    def process() {
      // ... skipped fragment
      amet.process()
    }
  }

  /** Amet component
    * @param id The id to pass to println
    * @param date The date to pass to println
    * @param getSize The function returning the size to pass to println
    */
  class Amet(id: Int, date: Date, getSize: () => Long) {

    /** Does Amet processing */
    def process() {
      // ... skipped fragment
      println(s"Hello world! Got parameters: " +
        s"($id, $date, ${getSize()})")
    }
  }

  class SitSpec extends Specification with Mockito {

    "a sit" should {
      "invoke method of amet" in {
        // given
        val amet = mock[Amet]
        val sit = new Sit(amet)
        // when
        sit.process()
        // then
        there was one(amet).process()
      }
    }
  }
}
