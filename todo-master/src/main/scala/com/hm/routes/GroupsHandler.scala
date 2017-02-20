package com.hm.routes

import com.hm.connector.MysqlClient
import spray.json.{JsArray, JsNumber, JsString, _}
import spray.routing.HttpService

/**
  * Created by vishnu on 2/17/17.
  */
trait GroupsHandler extends HttpService{



  /*
  *
  * Json Parameter = {"id":"1",
  * "gName":"demo",
  * "listOfUsers":["1","2","3"]
  * }


   }
  * */


  def createGrp = optionalCookie("userName") {
    case Some(nameCookie) => {
      val userId = nameCookie.content.toInt


    post {
      entity(as[String]) {
        body => {
          val json = body.parseJson.asJsObject

          val gName = json.getFields("gName").head.asInstanceOf[JsString].value
          if (!createGroup(gName,userId)) {

            complete("created successfuly")
          }
          else {
            complete("group creation failed")
          }
        }
      }
    }
    }
    case None => complete("No user logged in")
  }

 /* def adduser = post{
    entity(as[String]){
      body=>{
        val json = body.parseJson.asJsObject
        val rs = Mysqlclient.getResultSet("select * from user where user_name='"+username+"' AND password='"+password+"'")

      }
    }
  }*/

  /*def addUsr = post{
    entity(as[String])
    {
      body=>{
        val json = body.parseJson.asJsObject

      }
    }
  }
*/
//  def createGroupApi(gName:String,noOfParticipants:Int, listOfUsers:Array[Int])= {
//    val rs=Mysqlclient.executeQuery("insert into group values ("+1+",'"+gName+"','"++"')")
//    rs
//  }
def createGroup(  gName:String,userId:Int )={

   val groupId= MysqlClient.insert("grp",Map("name"->gName))
 val status= MysqlClient.executeQuery("insert into grp_users values("+userId+","+groupId+","+1+")")
  status
}
  def addUsers(uId:Int , gId:Int , role:Int)={
    val status = MysqlClient.executeQuery("insert into grp_users(u_id , g_id , admin_role) values ('"+uId+"','"+gId+"''"+role+"')")
    status
  }



}
