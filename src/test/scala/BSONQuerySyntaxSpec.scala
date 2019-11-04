import code._
import org.scalatest.{Matchers, WordSpec}
import reactivemongo.bson.{BSONArray, BSONDocument, BSONInteger}
import code.syntax._
import reactivemongo.bson._

class BSONQuerySyntaxSpec extends WordSpec with Matchers {
  "eql" should {
    "return an Equals query" in {
//      val testData = eql("age", 10)
      val testData = "age" === 10
      val expectedResult = Equals("age", BSONInteger(10))

      testData shouldBe expectedResult
    }
  }

  "and" should {
    "return an And query" in {
      val q1 = eql("age", 10)
      val q2 = eql("age", 11)
      val testData = q1 && q2
      val expectedResult = And(q1, q2)

      testData shouldBe expectedResult
    }
  }
}
