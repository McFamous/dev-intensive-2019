package ru.skillbranch.devintensive

import org.junit.Test

import org.junit.Assert.*
import ru.skillbranch.devintensive.extensions.*
import ru.skillbranch.devintensive.models.*
import ru.skillbranch.devintensive.utils.Utils
import java.util.*

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
class ExampleUnitTest {
    @Test
    fun addition_isCorrect() {
        assertEquals(4, 2 + 2)
    }
    @Test
    fun test_instance(){
//        val user = User("1")
        val user2 = User("2" , "John", "Cena")
//        val user3 = User("3", "John", "Silverhand", null, lastVisit = Date(), isOnline = true)

//        user.printMe()
//        user2.printMe()
//        user3.printMe()

    }

    @Test
    fun test_factory(){
        val user = User.makeUser("John")
//        val user2 = User.makeUser("John Wick")
//        val user3 = User.makeUser("John Snow")//здесь баг, при вводе пустой строки или null, программа некорректна

        val user2 = user.copy(id = "2", lastName = "Wick", lastVisit = Date())// 53:20
        print("$user \n$user2")
    }

    @Test
    fun test_decomposition(){
        val user = User.makeUser("John Wick")
        fun getUser() = user
        val (id, firstName, lastName) = getUser()
        println("$id, $firstName, $lastName")
        println("${user.component1()}, ${user.component2()}, ${user.component3()}")

    }

    @Test
    fun test_copy(){
        val user = User.makeUser("John Wick")
        var user2 = user.copy(lastVisit = Date())
        var user3 = user.copy(lastVisit = Date().add(-2,TimeUnits.SECOND))
        var user4 = user.copy(lastName = "Cena", lastVisit = Date().add(2,TimeUnits.HOUR))

        println("""
            ${user.lastVisit?.format()}
            ${user2.lastVisit?.format()}
            ${user3.lastVisit?.format()}
            ${user4.lastVisit?.format("HH:mm")}

        """.trimIndent())
    }

    @Test
    fun test_data_maping(){
        val user = User.makeUser("Кирилл Фарафонов")
//        val newUser = user.copy(lastVisit = Date().add(-7,TimeUnits.SECOND))
//        println(user)
//
//        val userView = user.toUserView();
//        val newUserView = newUser.toUserView();
//
//        userView.printMe()
//        newUserView.printMe()



    }

    @Test
    fun test_abstract_factory(){ // 1:39:00 - сдача домашнего задания
        val user = User.makeUser("Макеев Михаил")
        val textMessage = BaseMessage.makeMessage(user, Chat("0"), payload = "any text message", type = "text")
        val imageMessage = BaseMessage.makeMessage(user, Chat("0"), payload = "any image url", type = "image")

        println(textMessage.formatMessage())
        println(imageMessage.formatMessage())

    }

    @Test
    fun test_homework(){ // 1:39:00 - сдача домашнего задания

        println(Utils.parseFullName(null))
        println(Utils.parseFullName(""))
        println(Utils.parseFullName(" "))
        println(Utils.parseFullName("John"))

        println(Date().format())
        println(Date().format("HH:mm"))

        println(Date().add(2, TimeUnits.SECOND))
        println(Date().add(-4, TimeUnits.DAY))


        println(Utils.toInitials("john" ,"doe"))
        println(Utils.toInitials("John", null))
        println(Utils.toInitials(null, "xyn"))
        println(Utils.toInitials(null, null))
        println(Utils.toInitials(" ", ""))


        println(Utils.transliteration("Женя Стереотипов"))
        println(Utils.transliteration("Amazing Петр","_"))

        println(Date().add(-2, TimeUnits.HOUR))

        println(Date().add(-2, TimeUnits.HOUR).humanizeDiff())
        println(Date().add(-5, TimeUnits.DAY).humanizeDiff())
        println(Date().add(2, TimeUnits.MINUTE).humanizeDiff())
        println(Date().add(7, TimeUnits.DAY).humanizeDiff())
        println(Date().add(-400, TimeUnits.DAY).humanizeDiff())
        println(Date().add(400, TimeUnits.DAY).humanizeDiff())
        println(Date().humanizeDiff())

        println("Bender Bending Rodriguez — дословно «Сгибальщик Сгибающий Родригес»".truncate())
        println("Bender Bending Rodriguez — дословно «Сгибальщик Сгибающий Родригес»".truncate(15))
        println("A     ".truncate(3))

        println("<p class=\"title\">Образовательное IT-сообщество Skill Branch</p>".stripHtml())
        println("<p>Образовательное       IT-сообщество Skill Branch</p>".stripHtml())

        println(TimeUnits.SECOND.plural(1))
        println(TimeUnits.MINUTE.plural(4))
        println(TimeUnits.HOUR.plural(19))
        println(TimeUnits.DAY.plural(222))

    }
    @Test
    fun test_bender(){
        var benderObj : Bender = Bender(Bender.Status.NORMAL,Bender.Question.SERIAL)
        println(benderObj.listenAnswer("Роберт"))
        println(benderObj.listenAnswer("Роберт758765"))
        println(benderObj.listenAnswer("6757656"))
        println(benderObj.listenAnswer("888888888"))
        println(benderObj.listenAnswer("0000ерт"))

        println(Profile(3,4,"Женя","Стереотипов","lol","hhtpp").nickName())
    }
}
