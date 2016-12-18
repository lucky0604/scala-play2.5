package controllers

import javax.inject.{Inject, Singleton}

import models.{User, UserForm}
import play.api._
import play.api.mvc._
import services.UserService

import scala.concurrent.Future
import scala.concurrent.ExecutionContext.Implicits.global

@Singleton
class Application @Inject()(userService: UserService) extends Controller {

  def index = Action.async { implicit request =>
    userService.listAllUsers map { users =>
      Ok(views.html.index(UserForm.form, users))
    }
  }

  def addUser() = Action.async { implicit request =>
    UserForm.form.bindFromRequest.fold(
      // if any error in submitted data
      errorForm => Future.successful(Ok(views.html.index(errorForm, Seq.empty[User]))),
      data => {
        val newUser = User(0, data.firstName, data.lastName, data.mobile, data.email)
        userService.addUser(newUser).map(res =>
          Redirect(routes.Application.index())
        )
      }
    )
  }

  def deleteUser(id: Long) = Action.async { implicit request =>
    userService.deleteUser(id) map { res =>
      Redirect(routes.Application.index())
    }
  }

}