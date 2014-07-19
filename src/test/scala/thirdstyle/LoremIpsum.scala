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
        val ctx = new Context(id)
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
 *         The Exterior module                              *
 * -------------------------------------------------------- */
package exterior {
  import supercentrum._

  /** Lorem component
    * @param ipsum The SuperIpsum that continues processing
    */
  class Lorem[T](ipsum: SuperIpsum[T]) {

    /** Does Lorem processing
      * @param ctx The context to pass to ipsum
      */
    def process(ctx: T) {
      // ... skipped fragment that ignores the ctx
      ipsum.process(ctx)
    }
  }

  class LoremSpec extends Specification with Mockito {

    "a lorem" should {
      "invoke method of ipsum" in {
        // given
        //   an instance
        val ipsum = mock[SuperIpsum[AnyRef]]
        val lorem = new Lorem(ipsum)
        //   and a completely random context
        val ctx = mock[AnyRef]
        // when
        lorem.process(ctx)
        // then
        //   the collaborator must be called
        //   with exactly the same context
        there were noCallsTo(ctx)
        there was one(ipsum).process(ctx)
      }
    }
  }
}

/* ________________________________________________________ *
 *                 The SuperCentrum module                  *
 * -------------------------------------------------------- */
package supercentrum {

  /** Ipsum component */
  trait SuperIpsum[T] {

    /** Does Ipsum processing
      * @param ctx The context
      */
    def process(ctx: T)
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
  class Ipsum[T](dolor: Dolor[T]) extends SuperIpsum[T] {

    /** Does Ipsum processing
      * @param ctx The context to pass to dolor
      */
    def process(ctx: T) {
      // ... skipped fragment that ignores the ctx
      dolor.process(ctx)
    }
  }

  /** Dolor component
    * @param sit The SuperSit that continues processing
    */
  class Dolor[T](sit: SuperSit[T]) {

    /** Does Dolor processing
      * @param ctx The context to pass to sit
      */
    def process(ctx: T) {
      // ... skipped fragment that ignores the ctx
      sit.process(ctx)
    }
  }

  class IpsumSpec extends Specification with Mockito {

    "a ipsum" should {
      "invoke method of dolor" in {
        // given
        //   an instance
        val dolor = mock[Dolor[AnyRef]]
        val ipsum = new Ipsum(dolor)
        //   and a completely random context
        val ctx = mock[AnyRef]
        // when
        ipsum.process(ctx)
        // then
        //   the collaborator must be called
        //   with exactly the same context
        there were noCallsTo(ctx)
        there was one(dolor).process(ctx)
      }
    }
  }

  class DolorSpec extends Specification with Mockito {

    "a dolor" should {
      "invoke method of sit" in {
        // given
        //   an instance
        val sit = mock[SuperSit[AnyRef]]
        val dolor = new Dolor(sit)
        //   and a completely random context
        val ctx = mock[AnyRef]
        // when
        dolor.process(ctx)
        // then
        //   the collaborator must be called
        //   with exactly the same context
        there were noCallsTo(ctx)
        there was one(sit).process(ctx)
      }
    }
  }
}
/* ________________________________________________________ *
 *         The SuperInterior module                         *
 * -------------------------------------------------------- */
package superinterior {

  /** Sit component */
  trait SuperSit[T] {

    /** Does Sit processing
      * @param ctx The context
      */
    def process(ctx: T)
  }
}
/* ________________________________________________________ *
 *         The Interior module                              *
 * -------------------------------------------------------- */
package interior {
  import superinterior._

  /** Holds request parameters for passing between calls
    * @param id The id
    */
  class Context(
    var id: Int
  )

  /** Sit component implementation
    * @param amet The Amet that continues processing
    */
  class Sit[T](amet: SuperAmet[T]) extends SuperSit[T] {

    /** Does Sit processing
      * @param ctx The context to pass to amet
      */
    def process(ctx: T) {
      // ... skipped fragment that ignores the ctx
      amet.process(ctx)
    }
  }

  trait SuperAmet[T] {
    def process(ctx: T)
  }

  /** Amet component */
  class Amet extends SuperAmet[Context] {

    /** Does Amet processing
      * @param ctx The context to pass to println
      */
    def process(ctx: Context) {
      // ... skipped fragment that ignores the ctx
      println(s"Hello world! Got parameters: (${ctx.id})")
    }
  }

  class SitSpec extends Specification with Mockito {

    "a sit" should {
      "invoke method of amet" in {
        // given
        //   an instance
        val amet = mock[SuperAmet[AnyRef]]
        val sit = new Sit(amet)
        //   and a completely random context
        val ctx = mock[AnyRef]
        // when
        sit.process(ctx)
        // then
        //   the collaborator must be called
        //   with exactly the same context
        there were noCallsTo(ctx)
        there was one(amet).process(ctx)
      }
    }
  }
}
