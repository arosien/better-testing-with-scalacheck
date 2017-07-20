package simple

import org.scalacheck._

class StringProperties extends Properties("String") {
  property("startsWith") =
    Prop.forAll { (prefix: String, suffix: String) =>
      (prefix + suffix).startsWith(prefix)
    }

  property("endsWith") =
    Prop.forAll { (prefix: String, suffix: String) =>
      (prefix + suffix).endsWith(suffix)
    }
}