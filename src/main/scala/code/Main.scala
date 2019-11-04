package code

import reactivemongo.api.collections.bson.BSONCollection
import reactivemongo.api.{MongoConnection, MongoDriver}
import reactivemongo.bson.{BSONArray, BSONDocument, BSONInteger, BSONString}

import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.Future

object Main extends App {
  val driver = MongoDriver()

  def collection: Future[BSONCollection] =
    for {
      uri <- Future.fromTry(MongoConnection.parseURI(s"mongodb://localhost:27017"))
      conn <- Future.fromTry(driver.connection(uri, strictUri = true))
      db <- conn.database("olympics")
    } yield db.collection[BSONCollection]("medals")

  val query: BSONDocument =
    BSONDocument(List(
      "$and" -> BSONArray(List(
        BSONDocument(List("team" -> BSONString("GBR"))),
        BSONDocument(List("gold" -> BSONInteger(1))),
      ))
    ))

  //  def program: Future[List[BSONDocument]] =
  //    collection.flatMap { collection =>
  //      collection.find(query, None).cursor[BSONDocument]().collect[List](-1)
  //    }

  //  println(Await.result(program, 10.seconds))
}