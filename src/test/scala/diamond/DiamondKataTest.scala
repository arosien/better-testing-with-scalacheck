package diamond

import org.scalacheck._

object DiamondKataTest extends DiamondKataLaws("DiamondKata", DiamondKata.diamond)

// Note: must be `abstract`!
abstract class DiamondKataLaws(name: String, diamond: Char => List[String]) extends Properties(name) {
  import Prop._

  property("oddNumberOfLines") =
    Prop.forAllNoShrink(Gen.alphaUpperChar) { (c: Char) =>
      val lines = diamond(c)

      s"${lines.size} % 2 != 1" |: lines.size % 2 == 1
    }

  property("innerLinesHaveTwoChars") =
    Prop.forAllNoShrink(Gen.alphaUpperChar) { (c: Char) =>
      val lines = diamond(c)
      def middle[A](l: List[A]): List[A] = l.tail.dropRight(1)
      def countNonSpaces(s: String): Int = s.filterNot(_.isSpaceChar).size

      lines.mkString("\n") |: middle(lines).forall(countNonSpaces(_) == 2)
    }

  property("mirroredOverCenter") =
    Prop.forAllNoShrink(Gen.alphaUpperChar) { (c: Char) =>
      val lines = diamond(c)

      lines.mkString("\n") |: lines == lines.reverse
    }
}