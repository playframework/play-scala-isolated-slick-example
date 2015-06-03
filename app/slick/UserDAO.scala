package slick

import javax.inject.{Singleton, Inject}

import com.typesafe.config.Config
import models._

import scala.concurrent.Future
import slick.driver.PostgresDriver.api._

/**
 *
 */
@Singleton
class UserDAO @Inject() (config:Config, db:Database) {

  import slick.driver.PostgresDriver.api._

  private val users = TableQuery[Users]

  private val queryById = Compiled(
    (id: Rep[MyID]) => users.filter(_.id === id))


  def lookup(id: MyID): Future[Option[User]] = {
    db.run(queryById(id).result.headOption)
  }

  def all: Future[Seq[User]] = {
    db.run(users.result)
  }

  def update(user:User) = {
    db.run(queryById(user.id).update(user))
  }

  def delete(id:MyID) = {
    db.run(queryById(id).delete)
  }

  def create(user:User): Future[Int] = {
    db.run(
      users += user
    )
  }

  class Users(tag: Tag) extends Table[User](tag, "USERS") {
    def id = column[MyID]("ID", O.PrimaryKey)

    def email = column[String]("EMAIL")

    def * = (id, email) <> (User.tupled, User.unapply)
  }
}
