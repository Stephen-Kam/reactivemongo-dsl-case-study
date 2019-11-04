package code

import reactivemongo.bson.{BSONArray, BSONDocument, BSONValue}

sealed trait BSONQuery {
  def toBsonDoc: BSONDocument

  def &&(q1: BSONQuery): BSONQuery = And(this, q1)
}

case class LessThan(fieldname: String, value: Int) extends BSONQuery {
  override def toBsonDoc: BSONDocument = BSONDocument(fieldname -> BSONDocument("$lt" -> value))
}

case class GreaterThan(fieldname: String, value: Int) extends BSONQuery {
  override def toBsonDoc: BSONDocument = BSONDocument(fieldname -> BSONDocument("$gt" -> value))
}

case class Equals(fieldname: String, value: BSONValue) extends BSONQuery {
    override def toBsonDoc: BSONDocument = BSONDocument(fieldname -> BSONDocument("$eq" -> value ))
  }

case class NotEquals(fieldname: String, value: BSONValue) extends BSONQuery {
  override def toBsonDoc: BSONDocument = BSONDocument(fieldname -> BSONDocument("$ne" -> value ))
}

case class And(queryOne: BSONQuery, queryTwo: BSONQuery) extends BSONQuery {
  override def toBsonDoc: BSONDocument = BSONDocument("$and" -> BSONArray(queryOne.toBsonDoc, queryTwo.toBsonDoc))
}

case class Or(queryOne: BSONQuery, queryTwo: BSONQuery) extends BSONQuery {
  override def toBsonDoc: BSONDocument = BSONDocument("$or" -> BSONArray(queryOne.toBsonDoc, queryTwo.toBsonDoc))
}

case class Not(queryOne: BSONQuery) extends BSONQuery {
  override def toBsonDoc: BSONDocument = BSONDocument("$not" -> queryOne.toBsonDoc)
}

