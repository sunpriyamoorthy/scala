package com.hm.routes



import spray.json.JsString
import com.hm.connector.MysqlClient
import spray.routing.HttpService
import spray.json._
import com.hm.connector.MysqlClient

/**
  * Created by vishnu on 2/17/17.
  */
trait TodoHandler extends HttpService{
  def deleteToDo = post {
    entity(as[String]){
      body =>{
        val json = body.parseJson.asJsObject
        val todo_id = json.getFields("todo_id").head.asInstanceOf[JsString].value
        if(!deleteTask(todo_id.toInt)){
          complete("delete successful")
        }
        else{
          complete("delete failed")
        }
      }
    }
  }

  def updateTodo = post {
    entity(as[String]) {
      body => {
        val json = body.parseJson.asJsObject
        val todo_id = json.getFields("todo_id").head.asInstanceOf[JsString].value
        val message = json.getFields("message").head.asInstanceOf[JsString].value
        if (!updateTodoApi(todo_id.toInt, message)) {
          complete("update successful")
        }
        else {
          complete("update failed")
        }
      }
    }

  }




  def addToDo=post{

    entity(as[String]) {
      body => {
        val json = body.parseJson.asJsObject
        val user_id=json.getFields("user_id").head.asInstanceOf[JsString].value
        val message = json.getFields("message").head.asInstanceOf[JsString].value
        if (!insertTodo(message,user_id.toInt)) {

          complete("to do added")
        }else {
          complete("not added")
        }
      }
    }

  }






  def updateTodoApi(todo_id: Int, message: String) = {
    val rs = MysqlClient.executeQuery("update todo set message='" + message + "' where todo_id=" + todo_id + "")
    rs
  }
  def deleteTask(todo_id:Int) ={
    val rs = MysqlClient.executeQuery("delete from todo where todo_id= '" + todo_id +"'")
    rs
  }

  def insertTodo(message:String,userID:Int)={
    val rs=MysqlClient.executeQuery("insert into todo(user_id,message) values ("+userID+",'"+message+"')")
    rs
  }

}
