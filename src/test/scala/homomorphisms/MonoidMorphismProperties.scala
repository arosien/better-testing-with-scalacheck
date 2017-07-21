package homomorphisms

import cats._
import cats.implicits._
import org.scalacheck._

abstract class MonoidMorphismProperies[A, B : Eq](name: String, mm: MonoidMorphism[A, B], genA: Gen[A]) extends Properties(name) {
  import cats.laws.discipline._ // implicit conversion from cats.laws.IsEq[_] => Prop

  property(s"zeros") =
    mm.laws.zerosLaw

  property(s"distributes") =
    Prop.forAll(genA, genA) { (a1: A, a2: A) =>
      mm.laws.distributesLaw(a1, a2)
    }
}

object BooleanProperties extends MonoidMorphismProperies(
  "Boolean",
  Booleans.BooleanMonoidMorphism,
  Gen.oneOf(true, false))

import StableSorts._

object StableSortsProperties extends MonoidMorphismProperies[StableSorts.Comparator[Int], StableSorts.Sorter[Int]](
  "StableSorts",
  StableSorts.ComparatorSorterMorphism[Int],
  (i1: Int, i2: Int) => StableSorts.Equal)