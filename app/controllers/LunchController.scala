package controllers

import scala.concurrent.ExecutionContext.Implicits.global

import play.api.libs.json.Json
import play.api.mvc.Controller

import com.google.inject.Inject
import exceptions.ParticipantService
import mappers.LunchMapper
import models.Formats._
import models._
import services.{Authenticated, LunchService}

class LunchController @Inject()(lunchService: LunchService, participantService: ParticipantService) extends Controller {

  def createLunch() = Authenticated.async(parse.json) { request =>
    val lunchDto = request.body.as[CreateLunchDto]
    lunchService.createLunch(request.username, lunchDto).map(lunchId =>
      Created(Json.toJson(lunchId))
    )
  }

  def joinLunch(lunchId: Int) = Authenticated.async { request =>
    participantService.addUserToLunch(request.username, lunchId).map(participant =>
      Ok(Json.toJson(participant))
    )
  }

  def leaveLunch = Authenticated.async(parse.json) { request =>
    val lunchDto = request.body.as[MyLunchDto]
    participantService.removeUserFromLunch(request.username, lunchDto.id).map(rowsUpdated =>
      Ok(Json.toJson("Success"))
    )
  }

  def getLunch = Authenticated.async { request =>
    lunchService.getAllLunchNotPast.map { lunchRestSeq =>
      val lunchSeq = lunchRestSeq.map(a => LunchMapper.map(a._1, a._2, a._3))
      Ok(Json.toJson(lunchSeq))
    }
  }

  def getMyLunch = Authenticated.async { request =>
    lunchService.getLunchForUserNotPast(request.username).map { lunchRestSeq =>
      val lunchSeq = lunchRestSeq.map(a => LunchMapper.map(a._1, a._2))
      Ok(Json.toJson(lunchSeq))
    }
  }

  def getLunchDetail(lunchId: Int) = Authenticated.async { request =>
    participantService.getParticipants(lunchId).flatMap { participants =>
      val detail = lunchService.getLunchDetail(lunchId)
      detail.map { a =>
        val lunchDetail: LunchDetailDto = LunchMapper.map(a._1, a._2, participants)
        Ok(Json.toJson(lunchDetail))
      }
    }
  }
}
