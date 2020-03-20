package ru.skillbranch.devintensive.models

data class Profile(
    val respect : Int = 0,
    val rating : Int = 0,
    val firstName : String,
    val lastName : String,
    val about : String,
    val repository : String
) {
    private val nickName : String = "John Doe" //TODO implement me
    val rank : String = "Junior Android Developer"
    fun toMap() : Map<String, Any> = mapOf(
        "nickName" to nickName,
        "rank" to rank,
        "respect" to respect,
        "rating" to rating,
        "firstName" to firstName,
        "lastName" to lastName,
        "about" to about,
        "repository" to repository
    )
}