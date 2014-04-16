package controllers

import play.api._
import play.api.mvc._
import play.api.data._
import play.api.data.Forms._
import models.Item
import play.api.libs.json


object Application extends Controller {
  val itemForm = Form(
    "label" -> nonEmptyText
  )
  def index = Action {
    Redirect(routes.Application.items)
  }
  def items = Action {
    Ok(views.html.index(Item.all(), itemForm))
  }
  
  def newItem = Action { implicit request =>
    itemForm.bindFromRequest.fold(
      errors => BadRequest(views.html.index(Item.all(), errors)),
      label => {
        Item.create(label)
        Redirect(routes.Application.items)
      }
    )
  }
  
  def deleteItem(id: Long) = Action {
    Item.delete(id)
    Redirect(routes.Application.items)
  }
  def voteItem(id: Long) = Action {
    Item.vote(id)
    Redirect(routes.Application.items)
  }
  def group(group:Int) = Action {
    Ok("nyao");
  }
}