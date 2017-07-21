package homomorphisms

import cats._

/** Monoid morphisms are structure-preserving maps between monoids.
  * That is for two monoids `(M, ⊕, e)` and `(N, ⊗, f)` a monoid morphism `h` is a function satisfying
  *
  *   `h(e) = f`
  *   `h(a ⊕ b) = h(a) ⊗ h(b)`
  *
  * That is, a monoid morphism preserves identity and composition. */
abstract class MonoidMorphism[A, B](implicit MA: Monoid[A], MB: Monoid[B]) {

  /** The homomorphism that maps an `A` to `B` while respecting both `Monoid`s. */
  def h(a: A): B // TODO: probably needs a better name

  def left(a1: A, a2: A): A = MA.combine(a1, a2)

  def right(a1: A, a2: A): B = MB.combine(h(a1), h(a2))

  object laws {
    import cats.laws._

    def zerosLaw() =
      h(MA.empty) <-> MB.empty

    def distributesLaw(a1: A, a2: A) =
      h(left(a1, a2)) <-> right(a1, a2)
  }
}

object MonoidMorphism {
  /** Return the implicit [[MonoidMorphism]], if it exists. */
  def apply[A : Monoid, B : Monoid](implicit MM: MonoidMorphism[A, B]): MonoidMorphism[A, B] =
    MM
}

object Booleans {
  /** We can translate between disjunction and conjunction monoids via ! (NOT).
    *
    * Here we go in one direction from disjunction to conjunction. We can write
    * the converse morphism the same way, although we aren't distinguishing
    * which is which via the type [[Boolean]]. We could use wrappers or type tags
    * to give unique types to "Boolean under disjunction" vs. "Boolean under
    * conjunction".
    */
  implicit val BooleanMonoidMorphism: MonoidMorphism[Boolean, Boolean] =
    new MonoidMorphism[Boolean, Boolean]()(
      new Monoid[Boolean] {
        def empty: Boolean = false
        def combine(b1: Boolean, b2: Boolean): Boolean = b1 || b2
      },
      new Monoid[Boolean] {
        def empty: Boolean = true
        def combine(b1: Boolean, b2: Boolean): Boolean = b1 && b2
      }) {
      def h(b: Boolean): Boolean = !b
    }
}

object StableSorts {
  sealed trait Compared
  case object LessThan extends Compared
  case object Equal extends Compared
  case object GreaterThan extends Compared

  type Comparator[A] = (A, A) => Compared

  implicit def ComparatorMonoid[A]: Monoid[Comparator[A]] =
    new Monoid[Comparator[A]] {
      def empty = (_, _) => Equal
      def combine(c1: Comparator[A], c2: Comparator[A]): Comparator[A] =
        (a1, a2) => {
          val c = c1(a1, a2)

          if (c == Equal) c2(a1, a2)
          else c
        }
    }

  type Sorter[A] = List[A] => List[A]

  // TODO: This is not a quality `Eq` implementation!
  implicit def SorterEq[A : Eq : Monoid] =
    new Eq[Sorter[A]] {
      def eqv(s1: Sorter[A], s2: Sorter[A]): Boolean =
        s1(List(Monoid[A].empty)) == s2(List(Monoid[A].empty))
    }

  implicit def SorterMonoid[A] =
    new Monoid[Sorter[A]] {
      val empty = identity[List[A]](_)
      def combine(s1: Sorter[A], s2: Sorter[A]) = s1 compose s2
    }

  implicit def ComparatorSorterMorphism[A : Monoid]: MonoidMorphism[Comparator[A], Sorter[A]] =
    new MonoidMorphism[Comparator[A], Sorter[A]] {
      def h(c: Comparator[A]): Sorter[A] =
        (sa: List[A]) => sa.sortWith(c(_, _) == LessThan)
    }

  // Rather than `_.sortWith(ca) compose _.sortWith(cb)`,
  //          do `_.sortWith(ca compose cb)`
}