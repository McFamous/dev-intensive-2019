package ru.skillbranch.devintensive.models

import ru.skillbranch.devintensive.utils.Utils
import java.util.*


data class User (
    val id:String,
    val firstName:String?,
    var lastName:String?,
    val avatar:String?,
    var rating:Int = 0,
    var respect:Int = 0,
    val lastVisit:Date? = null,
    val isOnline:Boolean = false){

    constructor(id: String, firstName: String?,lastName: String?): this(
        id = id,
        firstName = firstName,
        lastName = lastName,
        avatar = null)
    constructor(id: String):this(id, "John", "Doe")

    init { // блок кода который всегда вызывается
        println("It's Alive!!!\n" +
                "${if(lastName === "Doe") "His name id $firstName $lastName " else "And his name $firstName $lastName "}\n")
    }
    
    companion object Factory{
        private var lastId : Int = -1
        fun makeUser(fullName:String?): User{
            lastId++

            var (firstName, lastName) = Utils.parseFullName(fullName)
            return User(id = "$lastId", firstName = firstName, lastName = lastName)
        }
    }

    data class Builder(var id:String = "",
                       var firstName:String? = null,
                       var lastName:String? = null,
                       var avatar:String? = null,
                       var rating:Int = 0,
                       var respect:Int = 0,
                       var lastVisit:Date? = null,
                       var isOnline:Boolean = false){

        fun id(s:String){
            id = s
        }
        fun firstName(s:String){
            firstName = s
        }
        fun lastName(s:String){
            lastName = s
        }
        fun avatar(s:String){
            avatar = s
        }
        fun rating(n:Int){
            rating = n
        }
        fun respect(n:Int){
            respect = n
        }
        fun lastVisit(d:Date){
            lastVisit = d
        }
        fun isOnline(b:Boolean){
            isOnline = b
        }
        fun build() : User{
            return User(id, firstName, lastName, avatar, rating, respect, lastVisit, isOnline)
        }
    }

}