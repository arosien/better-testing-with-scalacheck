# Better Testing with Scalacheck

Check out the repo:

    $ git clone git@github.com:arosien/better-testing-with-scalacheck.git

Make sure everything compiles and runs:

    $ cd better-testing-with-scalacheck

    $ sbt test
    ...
    [error] Error: Total 13, Failed 0, Errors 8, Passed 5
    [error] Error during tests:
    [error]   diamond.DiamondKataTest
    [error]   simple.StringProperties
    [error]   stateful.CounterProperties
    [error] (test:test) sbt.TestsFailedException: Tests unsuccessful

**It's ok that the tests fail, we haven't written them all yet!**

## References

Libraries:

 * [ScalaCheck](https://github.com/rickynils/scalacheck)
   * [ScalaCheck API](http://scalacheck.org/files/scalacheck_2.11-1.13.4-api/index.html)
 * [scalaprops](https://github.com/scalaprops/scalaprops)
 * [Nyaya](https://github.com/japgolly/nyaya/)
   * [japgolly/test-state](https://github.com/japgolly/test-state)
 * [Hedgehog](https://github.com/hedgehogqa/scala-hedgehog)

Main sources:

 * [An introduction to property-based testing](http://fsharpforfunandprofit.com/posts/property-based-testing/)
 * [Choosing properties for property-based testing](https://fsharpforfunandprofit.com/posts/property-based-testing-2/)

Examples:

 * [Diamond Kata](http://natpryce.com/articles/000807.html)

Even more property-based testing:

 * [Exploring Test-Driven Development with QuickCheck](http://www.natpryce.com/articles/000795.html)
 * [Property Based TDD at SPA 2013](http://www.natpryce.com/articles/000802.html)
 * [How I learned to stop unit testing and love property-based testing](http://blog.charleso.org/property-testing-preso/yowlj2015.html)
 * [I Dream of Gen'ning: ScalaCheck is Black Magic](https://www.slideshare.net/kelseyinnis/scalacheck-2-41828057)
 * [Protocol Buffers and property-based testing](http://www.cakesolutions.net/teamblogs/protocol-buffers-and-property-based-testing)

Nerdy stuff:

 * [Thinking before programming](http://alistair.cockburn.us/Thinking+before+programming)
 * [Mysteries of Dropbox: Property-Based Testing of a Distributed Synchronization Service](https://www.cis.upenn.edu/~bcpierce/papers/mysteriesofdropbox.pdf)
 * [The Practice of Theories: Adding “For-all” Statements to “There-Exists” Tests](http://shareandenjoy.saff.net/tdd-specifications.pdf)
 * [Property-based Testing for Better Code](https://www.infoq.com/presentations/property-based-testing)