package diamond

import org.scalacheck._

class DiamondKataTest extends DiamondKataLaws("DiamondKata", DiamondKata.diamond)

// Note: must be `abstract`, otherwise `java.lang.NoSuchMethodException: diamond.DiamondKataLaws.<init>()`
abstract class DiamondKataLaws(name: String, diamond: Char => List[String]) extends Properties(name) {
  import Prop._

  property("oddNumberOfLines") =
    Prop.forAllNoShrink(Gen.alphaUpperChar) { (c: Char) =>
      val lines = diamond(c)

      lines.size % 2 == 1
    }

  property("2n + 1 lines") =
    Prop.forAllNoShrink(Gen.alphaUpperChar) { (c: Char) =>
      val lines = diamond(c)

      lines.size == 2 * (c - 'A') + 1
    }

  property("square") =
    Prop.forAllNoShrink(Gen.alphaUpperChar) { (c: Char) =>
      val lines = diamond(c)

      lines forall (_.size == lines.size)
    }

  property("innerLinesHaveTwoChars") =
    Prop.forAllNoShrink(Gen.alphaUpperChar) { (c: Char) =>
      val lines = diamond(c)
      def middle[A](l: List[A]): List[A] = l.tail.dropRight(1)
      def countNonSpaces(s: String): Int = s.filterNot(_.isSpaceChar).size

      middle(lines).forall(countNonSpaces(_) == 2)
    }

  property("top-bottom symmetry") =
    Prop.forAllNoShrink(Gen.alphaUpperChar) { (c: Char) =>
      val lines = diamond(c)

      lines == lines.reverse
    }

  property("left-right symmetry") =
    Prop.forAllNoShrink(Gen.alphaUpperChar) { (c: Char) =>
      val lines = diamond(c)

      lines forall (line => line == line.reverse)
    }
}