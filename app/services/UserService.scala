package services

import javax.inject.{Inject, Singleton}

import models.{User, Users}

import scala.concurrent.Future


/**
  * Created by lucky on 16-12-18.
  */
@Singleton
class UserService @Inject()(users: Users) {
  def addUser(user: User): Future[String] = {
    users.add(user)
  }

  def deleteUser(id: Long): Future[Int] = {
    users.delete(id)
  }

  def getUser(id: Long): Future[Option[User]] = {
    users.get(id)
  }

  def listAllUsers: Future[Seq[User]] = {
    users.listAll
  }
}
