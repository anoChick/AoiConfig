package models
import anorm._
import anorm.SqlParser._
import play.api.db._
import play.api.Play.current
case class Item(id: Long, label: String, vote: Int , group: Int )

object Item {


  val item = {
    get[Long]("id") ~ 
    get[String]("label") ~ 
    get[Int]("vote") ~ 
    get[Int]("item_group") map {
      case id~label~vote~group => Item(id, label,vote,group)
    }
  }
  def all(): List[Item] = DB.withConnection { implicit c =>
    SQL("select * from item").as(item * )
  }
  
  def create(label: String) {
    DB.withConnection { implicit c =>
      SQL("insert into item (label , vote , item_group) values ({label} , 0 , 1)").on(
        'label -> label
      ).executeUpdate()
    }
  }  
  def delete(id: Long) {
    DB.withConnection { implicit c =>
      SQL("delete from item where id = {id}").on(
        'id -> id
      ).executeUpdate()
    }
  }
  def vote(id: Long) {
    DB.withConnection { implicit c =>
      SQL("update item set vote = (vote+1) where id = {id}").on(
        'id -> id
      ).executeUpdate()
    }
  }
}