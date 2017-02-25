package controllers

import javax.inject.{Inject, Singleton}

import com.example.user.UserDAO
import play.api.mvc._

import scala.concurrent.ExecutionContext

@Singleton
class HomeController @Inject() (userDAO: UserDAO)
                               (implicit ec: ExecutionContext)
  extends Controller {

  def index = Action.async { implicit request =>
    userDAO.all.map { users =>
      Ok(views.html.index(users))
    }
  }

}
