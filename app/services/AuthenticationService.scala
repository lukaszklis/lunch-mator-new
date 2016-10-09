package services

import javax.inject.Inject
import models.UserIdentity
import persistence.repository.UserIdentities
import play.api.db.slick.DatabaseConfigProvider

class AuthenticationService @Inject()(dbConfigProvider: DatabaseConfigProvider) extends Service(dbConfigProvider) {

  def signUp(userIdentity: UserIdentity) = usingDB {
    UserIdentities.createNewUser(UserIdentityService.map(userIdentity))
  }

  def getUserByEmail(email: String) = usingDB {
    UserIdentities.getUserIdentity(email).head
  }
}