package ru.skillbranch.devintensive.models

import java.lang.NumberFormatException
import java.util.regex.Matcher
import java.util.regex.Pattern

class Bender(
    var status:Status = Status.NORMAL,
    var question:Question = Question.NAME) {

    fun askQuestion() : String = when(question){
        Question.NAME -> Question.NAME.question
        Question.PROFESSION -> Question.PROFESSION.question
        Question.MATERIAL -> Question.MATERIAL.question
        Question.BDAY -> Question.BDAY.question
        Question.SERIAL -> Question.SERIAL.question
        Question.IDLE -> Question.IDLE.question
    }

    fun listenAnswer(answer : String) : Pair<String, Triple<Int, Int, Int>>{
        val pattern : Pattern = Pattern.compile("[0-9]")
        val matcher : Matcher = pattern.matcher(answer)
        return if(question.answers.contains(answer)){
            question = question.nextQuestion()
            "Отлично - ты справился\n${question.question}" to status.color
        }
        else{
            if(testOnLowerCase(answer) &&  question == Question.NAME){
                "${question.correctEnter()}\n${question.question}" to status.color
            }
            else if(!testOnLowerCase(answer) &&  question == Question.PROFESSION){//
                "${question.correctEnter()}\n${question.question}" to status.color
            }
            else if(matcher.find() && question == Question.MATERIAL ){//
                "${question.correctEnter()}\n${question.question}" to status.color
            }
            else if(!checkLetters(answer) && question == Question.BDAY ){
                "${question.correctEnter()}\n${question.question}" to status.color
            }
            else if((!checkLetters(answer) || answer.length != 7) && question == Question.SERIAL ){
                "${question.correctEnter()}\n${question.question}" to status.color
            }
            else if(question == Question.IDLE)
                "${question.correctEnter()}\n${question.question}" to status.color
            else{
                status = status.nextStatus()
                if(status == Status.NORMAL && question != Question.IDLE){
                    question = Question.NAME
                    "Это неправильный ответ. Давай все по новой\n${question.question}" to status.color
                }
                else
                    "Это неправильный ответ\n${question.question}" to status.color
            }
        }
    }

    enum class Status(val color : Triple<Int,Int,Int>){
        NORMAL(Triple(255, 255, 255)) ,
        WARNING(Triple(255, 120, 0)),
        DANGER(Triple(255, 60, 60)),
        CRITICAL(Triple(255, 0, 0)) ;

        fun nextStatus() : Status{
            return if(this.ordinal < values().lastIndex){
                values()[this.ordinal + 1]
            }else{
                values()[0]
            }
        }
    }
    enum class Question(val question:String, val answers:List<String>){

        NAME("Как меня зовут?", listOf("Бендер","Bender")) {
            override fun nextQuestion(): Question = PROFESSION
            override fun correctEnter(): String = "Имя должно начинаться с заглавной буквы"
        },
        PROFESSION("Назови мою профессию?", listOf("сгибальщик","bender")) {
            override fun nextQuestion(): Question = MATERIAL
            override fun correctEnter(): String = "Профессия должна начинаться со строчной буквы"
        },
        MATERIAL("Из чего я сделан?", listOf("дерево","металл", "metal","iron","wood")) {
            override fun nextQuestion(): Question = BDAY
            override fun correctEnter(): String = "Материал не должен содержать цифр"
        },
        BDAY("Когда меня создали?", listOf("2993")) {
            override fun nextQuestion(): Question = SERIAL
            override fun correctEnter(): String = "Год моего рождения должен содержать только цифры"
        },
        SERIAL("Мой серийный номер?", listOf("2716057")) {
            override fun nextQuestion(): Question = IDLE
            override fun correctEnter(): String = "Серийный номер содержит только цифры, и их 7"
        },
        IDLE("На этом все, вопросов больше нет", listOf()) {
            override fun nextQuestion(): Question = IDLE
            override fun correctEnter(): String = ""
        };

        abstract fun nextQuestion() : Question
        abstract fun correctEnter() : String
    }

    private fun testOnLowerCase(str : String) : Boolean{
        val lowerStr = str.toCharArray()
        return lowerStr[0] == lowerStr[0].toLowerCase()
    }
    private fun checkLetters(str : String) : Boolean{
        return try{
            str.toInt()
            true
        }
        catch ( e : NumberFormatException){
            false
        }
    }
}