package services

import javax.inject.Inject

import models.Restaurant
import persistence.repository.Restaurants
import play.api.db.slick.DatabaseConfigProvider

import scala.concurrent.Future

class RestaurantService @Inject()(val dbConfigDataProvider: DatabaseConfigProvider) {

  def createNewRestaurant(restaurant: Restaurant) = usingDB {
    Restaurants.addRestraurant(restaurant)
  }

  def getAllRestaurants: Future[Seq[Restaurant]] = usingDB {
    Restaurants.getAll
  }

  def getRestaurantByName(name: String): Future[Option[Restaurant]] = usingDB {
    Restaurants.getRestaurantsByName(name)
  }
}
