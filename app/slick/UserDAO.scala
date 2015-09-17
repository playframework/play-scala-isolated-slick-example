package slick

import javax.inject.{Singleton, Inject}

import com.typesafe.config.Config
import models.User

import scala.concurrent.Future
import com.typesafe.slick.driver.oracle.OracleDriver.api._

/**
 *
 */
@Singleton
class UserDAO @Inject() (config:Config, db:Database) {

  private val users = TableQuery[Users]

  private val queryById = Compiled(
    (id: Rep[String]) => users.filter(_.id === id))


  def lookup(id: String): Future[Option[User]] = {
    db.run(queryById(id).result.headOption)
  }

  def all: Future[Seq[User]] = {
    db.run(users.result)
  }

  def update(user:User) = {
    db.run(queryById(user.id).update(user))
  }

  def delete(id:String) = {
    db.run(queryById(id).delete)
  }

  def create(user:User): Future[Int] = {
    db.run(
      users += user
    )
  }

  class Users(tag: Tag) extends Table[User](tag, "USERS") {
    def id = column[String]("ID", O.PrimaryKey)
    def email = column[String]("EMAIL")

    def registered = column[Option[Boolean]]("REGISTERED")

    def * = (id, registered, email) <> (User.tupled, User.unapply)
  }
}
