package code

import org.scalatest.{FlatSpec, Matchers, WordSpec}
import reactivemongo.bson.{BSONArray, BSONDecimal, BSONDocument, BSONInteger}

class BSONQuerySpec extends WordSpec with Matchers {
  "GreaterThan" should {
    "return a bson document" in {
      val testData = GreaterThan("age", 10)
      val result = BSONDocument("age" -> BSONDocument("$gt" -> 10))

      testData.toBsonDoc shouldBe result
    }
  }

  "LessThan" should {
    "return a bson document" in {
      val testData = LessThan("age", 10)
      val result = BSONDocument("age" -> BSONDocument("$lt" -> 10))

      testData.toBsonDoc shouldBe result
    }
  }

  "And" should {
    "return a bson document" in {
      val q1 = LessThan("age", 10)
      val q2 = GreaterThan("age", 5)

      val testData = And(q1,q2)
      val expectedResult = BSONDocument("$and" -> BSONArray(q1.toBsonDoc, q2.toBsonDoc))

      testData.toBsonDoc shouldBe expectedResult
    }
  }

  "Or" should {
    "return a bson document" in {
      val q1 = LessThan("age", 10)
      val q2 = GreaterThan("age", 5)

      val testData = Or(q1,q2)
      val expectedResult = BSONDocument("$or" -> BSONArray(q1.toBsonDoc, q2.toBsonDoc))

      testData.toBsonDoc shouldBe expectedResult
    }
  }

  "Not" should {
    "return a bson document" in {
      val q1 = LessThan("age", 10)

      val testData = Not(q1)
      val expectedResult = BSONDocument("$not" -> q1.toBsonDoc)

      testData.toBsonDoc shouldBe expectedResult
    }
  }

  "Equal" should {
    "return a bson document" in {
      val testData = Equals("age", BSONInteger(10))
      val expectedResult = BSONDocument("age" -> BSONDocument("$eq" -> 10))

      testData.toBsonDoc shouldBe expectedResult
    }
  }

  "NotEqual" should {
    "return a bson document" in {
      val testData = NotEquals("age", BSONInteger(10))
      val expectedResult = BSONDocument("age" -> BSONDocument("$ne" -> 10))

      testData.toBsonDoc shouldBe expectedResult
    }
  }
}
