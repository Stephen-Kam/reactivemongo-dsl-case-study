package code

import reactivemongo.bson.{BSONValue, BSONWriter}

object syntax {

  def eql[T, B <: BSONValue](fieldName: String, value: T)(implicit encoder: BSONWriter[T, B]): BSONQuery =
    Equals(fieldName, encoder.write(value))

  def and(q1: BSONQuery, q2: BSONQuery): BSONQuery = And(q1, q2)

  implicit class FieldNameOps(fieldName: String) {
    def ===[T, B <: BSONValue](value: T)(implicit encoder: BSONWriter[T, B]): BSONQuery =
      Equals(fieldName, encoder.write(value))

  }
}
