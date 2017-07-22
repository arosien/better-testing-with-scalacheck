package stateful

import org.scalacheck._
import org.scalacheck.commands.Commands
import scala.util._

object CounterProperties extends Properties("Counter") {
  property("commands") = CounterSpecification.property()
}

// I totally stole this from https://github.com/rickynils/scalacheck/blob/master/doc/UserGuide.md
object CounterSpecification extends Commands {
  import Prop._

  type State = Int

  type Sut = Counter

  def canCreateNewSut(newState: State, initSuts: Traversable[State],
    runningSuts: Traversable[Sut]): Boolean = true

  def initialPreCondition(state: State): Boolean = {
    // Since the counter implementation doesn't allow initialisation with an
    // arbitrary number, we can only start from zero
    state == 0
  }

  def newSut(state: State): Sut = new Counter

  def destroySut(sut: Sut): Unit = ()

  def genInitialState: Gen[State] = Gen.const(0)

  def genCommand(state: State): Gen[Command] = Gen.oneOf(Inc, Get, Dec, Reset)

  case object Inc extends UnitCommand {
    def run(sut: Sut): Unit = ???
    def nextState(state: State): State = ???
    def preCondition(state: State): Boolean = ???
    def postCondition(state: State, success: Boolean): Prop = ???
  }

  case object Dec extends UnitCommand {
    def run(sut: Sut): Unit = ???
    def nextState(state: State): State = ???
    def preCondition(state: State): Boolean = ???
    def postCondition(state: State, success: Boolean): Prop = ???
  }

  case object Reset extends UnitCommand {
    def run(sut: Sut): Unit = ???
    def nextState(state: State): State = ???
    def preCondition(state: State): Boolean = ???
    def postCondition(state: State, success: Boolean): Prop = ???
  }

  case object Get extends Command {
    type Result = Int

    def run(sut: Sut): Result = ???
    def nextState(state: State): State = ???
    def preCondition(state: State): Boolean = ???
    def postCondition(state: State, result: Try[Result]): Prop = ???
  }
}