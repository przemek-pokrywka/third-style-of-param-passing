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
import org.mockito.ArgumentCaptor.{forClass => argumentCaptor}

/* ________________________________________________________ *
 *           The Bootstrap module                           *
 * -------------------------------------------------------- */
package bootstrap {
  // Depends on every other module
  import exterior._
  import centrum._
  import interior._
  import commons._

  /** Main class */
  object Application {

    def main (args: Array[String]): Unit = {
      val factory = new Factory()
      val lorem = factory.lorem
      for (i <- 1 to 3) {
        val id = randomInt()
        val size = args.length.toLong
        val ctx = new Context(id, size)
        lorem.process(ctx)
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
 *         The Commons module                               *
 * -------------------------------------------------------- */
package commons {

  /** Holds request parameters for passing between calls
    * @param id The id
    */
  class Context(
    var id: Int,
    var size: Long
  )
}
/* ________________________________________________________ *
 *         The Exterior module                              *
 * -------------------------------------------------------- */
package exterior {
  import supercentrum._
  import commons._

  /** Lorem component
    * @param ipsum The SuperIpsum that continues processing
    */
  class Lorem(ipsum: SuperIpsum) {

    /** Does Lorem processing
      * @param ctx The context to pass to ipsum
      */
    def process(ctx: Context) {
      // ... skipped fragment that ignores the ctx
      ipsum.process(ctx)
    }
  }

  class LoremSpec extends Specification with Mockito {

    "a lorem" should {
      "invoke method of ipsum" in {
        // given
        //   an instance
        val ipsum = mock[SuperIpsum]
        val lorem = new Lorem(ipsum)
        //   and a completely random context
        val id = randomInt()
        val size = randomLong()
        val ctx = new Context(id, size)
        // when
        lorem.process(ctx)
        // then
        //   the collaborator must be called
        //   with exactly the same context
        val captor = argumentCaptor(classOf[Context])
        there was one(ipsum).process(captor.capture())
        val captured = captor.getValue
        captured.id must beEqualTo(id)
        captured.size must beEqualTo(size)
      }
    }
  }
}

/* ________________________________________________________ *
 *                 The SuperCentrum module                  *
 * -------------------------------------------------------- */
package supercentrum {
  import commons._

  /** Ipsum component */
  trait SuperIpsum {

    /** Does Ipsum processing
      * @param ctx The context
      */
    def process(ctx: Context)
  }
}
/* ________________________________________________________ *
 *            The Centrum module                            *
 * -------------------------------------------------------- */
package centrum {
  import supercentrum._
  import superinterior._
  import commons._

  /** Ipsum component implementation
    * @param dolor The Dolor that continues processing
    */
  class Ipsum(dolor: Dolor) extends SuperIpsum {

    /** Does Ipsum processing
      * @param ctx The context to pass to dolor
      */
    def process(ctx: Context) {
      // ... skipped fragment that ignores the ctx
      dolor.process(ctx)
    }
  }

  /** Dolor component
    * @param sit The SuperSit that continues processing
    */
  class Dolor(sit: SuperSit) {

    /** Does Dolor processing
      * @param ctx The context to pass to sit
      */
    def process(ctx: Context) {
      // ... skipped fragment that ignores the ctx
      sit.process(ctx)
    }
  }

  class IpsumSpec extends Specification with Mockito {

    "a ipsum" should {
      "invoke method of dolor" in {
        // given
        //   an instance
        val dolor = mock[Dolor]
        val ipsum = new Ipsum(dolor)
        //   and a completely random context
        val id = randomInt()
        val size = randomLong()
        val ctx = new Context(id, size)
        // when
        ipsum.process(ctx)
        // then
        //   the collaborator must be called
        //   with exactly the same context
        val captor = argumentCaptor(classOf[Context])
        there was one(dolor).process(captor.capture())
        val captured = captor.getValue
        captured.id must beEqualTo(id)
        captured.size must beEqualTo(size)
      }
    }
  }

  class DolorSpec extends Specification with Mockito {

    "a dolor" should {
      "invoke method of sit" in {
        // given
        //   an instance
        val sit = mock[SuperSit]
        val dolor = new Dolor(sit)
        //   and a completely random context
        val id = randomInt()
        val size = randomLong()
        val ctx = new Context(id, size)
        // when
        dolor.process(ctx)
        // then
        //   the collaborator must be called
        //   with exactly the same context
        val captor = argumentCaptor(classOf[Context])
        there was one(sit).process(captor.capture())
        val captured = captor.getValue
        captured.id must beEqualTo(id)
        captured.size must beEqualTo(size)
      }
    }
  }
}
/* ________________________________________________________ *
 *         The SuperInterior module                         *
 * -------------------------------------------------------- */
package superinterior {
  import commons._

  /** Sit component */
  trait SuperSit {

    /** Does Sit processing
      * @param ctx The context
      */
    def process(ctx: Context)
  }
}
/* ________________________________________________________ *
 *         The Interior module                              *
 * -------------------------------------------------------- */
package interior {
  import commons._
  import superinterior._

  /** Sit component implementation
    * @param amet The Amet that continues processing
    */
  class Sit(amet: Amet) extends SuperSit {

    /** Does Sit processing
      * @param ctx The context to pass to amet
      */
    def process(ctx: Context) {
      // ... skipped fragment that ignores the ctx
      amet.process(ctx)
    }
  }

  /** Amet component */
  class Amet {

    /** Does Amet processing
      * @param ctx The context to pass to println
      */
    def process(ctx: Context) {
      // ... skipped fragment that ignores the ctx
      println(s"Hello world! Got parameters: " +
        s"(${ctx.id}, ${ctx.size}})")
    }
  }

  class SitSpec extends Specification with Mockito {

    "a sit" should {
      "invoke method of amet" in {
        // given
        //   an instance
        val amet = mock[Amet]
        val sit = new Sit(amet)
        //   and a completely random context
        val id = randomInt()
        val size = randomLong()
        val ctx = new Context(id, size)
        // when
        sit.process(ctx)
        // then
        //   the collaborator must be called
        //   with exactly the same context
        val captor = argumentCaptor(classOf[Context])
        there was one(amet).process(captor.capture())
        val captured = captor.getValue
        captured.id must beEqualTo(id)
        captured.size must beEqualTo(size)
      }
    }
  }
}
