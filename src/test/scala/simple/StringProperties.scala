package simple

import org.scalacheck._

class StringProperties extends Properties("String") {
  property("startsWith") =
    Prop.forAll { (prefix: String, suffix: String) =>
      ???
    }

  property("endsWith") =
    Prop.forAll { (prefix: String, suffix: String) =>
      ???
    }
}