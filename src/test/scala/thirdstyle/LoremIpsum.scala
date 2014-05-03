package thirdstyle

/* Note: such code style is unusual in Scala. It's only for
 * illustrative purposes, to fit the whole app in one file.
 * Usually you mimic Java conventions, separating tests
 * from production code.
 */
import org.specs2.mutable.Specification
import org.specs2.mock.Mockito
import scala.util.Random.{nextInt => randomInt}

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
      val lorem = factory.lorem
      for (i <- 1 to 3) {
        val id = randomInt()
        lorem.process(id)
      }
    }
  }

  /** Factory responsible for wiring up objects */
  class Factory {
    val amet = new Amet()
    val sit = new Sit(amet)
    val dolor = new Dolor(sit)
    val ipsum = new Ipsum(dolor)
    val lorem = new Lorem(ipsum)
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

    /** Does Lorem processing
      * @param id The id to pass to ipsum
      */
    def process(id: Int) {
      // ... skipped fragment that ignores the id
      ipsum.process(id)
    }
  }

  class LoremSpec extends Specification with Mockito {

    "a lorem" should {
      "invoke method of ipsum" in {
        // given
        val ipsum = mock[SuperIpsum]
        val id = randomInt()
        val lorem = new Lorem(ipsum)
        // when
        lorem.process(id)
        // then
        there was one(ipsum).process(id)
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

    /** Does Ipsum processing
      * @param id The id
      */
    def process(id: Int)
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
    */
  class Ipsum(dolor: Dolor) extends SuperIpsum {

    /** Does Ipsum processing
      * @param id The id to pass to dolor
      */
    def process(id: Int) {
      // ... skipped fragment that ignores the id
      dolor.process(id)
    }
  }

  /** Dolor component
    * @param sit The SuperSit that continues processing
    */
  class Dolor(sit: SuperSit) {

    /** Does Dolor processing
      * @param id The id to pass to sit
      */
    def process(id: Int) {
      // ... skipped fragment that ignores the id
      sit.process(id)
    }
  }

  class IpsumSpec extends Specification with Mockito {

    "a ipsum" should {
      "invoke method of dolor" in {
        // given
        val dolor = mock[Dolor]
        val id = randomInt()
        val ipsum = new Ipsum(dolor)
        // when
        ipsum.process(id)
        // then
        there was one(dolor).process(id)
      }
    }
  }

  class DolorSpec extends Specification with Mockito {

    "a dolor" should {
      "invoke method of sit" in {
        // given
        val sit = mock[SuperSit]
        val id = randomInt()
        val dolor = new Dolor(sit)
        // when
        dolor.process(id)
        // then
        there was one(sit).process(id)
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

    /** Does Sit processing
      * @param id The id
      */
    def process(id: Int)
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

    /** Does Sit processing
      * @param id The id to pass to amet
      */
    def process(id: Int) {
      // ... skipped fragment that ignores the id
      amet.process(id)
    }
  }

  /** Amet component */
  class Amet {

    /** Does Amet processing
      * @param id The id to pass to println
      */
    def process(id: Int) {
      // ... skipped fragment that ignores the id
      println(s"Hello world! Got parameters: ($id)")
    }
  }

  class SitSpec extends Specification with Mockito {

    "a sit" should {
      "invoke method of amet" in {
        // given
        val amet = mock[Amet]
        val id = randomInt()
        val sit = new Sit(amet)
        // when
        sit.process(id)
        // then
        there was one(amet).process(id)
      }
    }
  }
}
