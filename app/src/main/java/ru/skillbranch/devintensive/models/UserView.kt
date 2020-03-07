package ru.skillbranch.devintensive.models

class UserView(
    val id : String,
    val fullName : String,
    val nickName : String,
    var avatar : String? = null,
    var status : String? = "offline",
    val initialise : String?
) {
    fun printMe(){
        println("""
            id: $id:
            fullName: $fullName:
            nickName: $nickName:
            avatar: $avatar:
            status: $status:
            initialise: $initialise:
        """.trimIndent())
    }
}