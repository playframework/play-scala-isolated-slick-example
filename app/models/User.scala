package models


//case class MyID(value: String) extends slick.lifted.MappedTo[String]

case class User(id:String, registered:Boolean, email:String)
