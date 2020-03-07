package ru.skillbranch.devintensive.extensions

import ru.skillbranch.devintensive.models.User
import ru.skillbranch.devintensive.models.UserView
import ru.skillbranch.devintensive.utils.Utils
import java.util.*

fun User.toUserView(): UserView{

    val nickName = Utils.transliteration("$firstName $lastName")
    val initialise = Utils.toInitials(firstName, lastName)
    val status = if(lastVisit == null) "Еще ни разу не был " else if (isOnline) "online" else "Был в сети ${lastVisit.humanizeDiff()}"

    return UserView(
        id,
        fullName = "$firstName $lastName",
        nickName = nickName,
        initialise = initialise,
        avatar = avatar,
        status = status )
}

