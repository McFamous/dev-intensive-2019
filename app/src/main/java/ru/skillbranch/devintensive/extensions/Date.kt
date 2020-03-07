package ru.skillbranch.devintensive.extensions

import java.nio.channels.IllegalSelectorException
import java.text.SimpleDateFormat
import java.util.*


const val SECOND = 1000L
const val MINUTE = 60 * SECOND
const val HOUR = 60 * MINUTE
const val DAY = 24 * HOUR
const val DAY_IN_SECOND = 86400

fun Date.format(pattern:String = "HH:mm::ss dd:MM:yy"):String{
    val dateFormat = SimpleDateFormat(pattern,Locale("ru"))
    return dateFormat.format(this)
}

fun Date.add(value:Int, units:TimeUnits = TimeUnits.SECOND) : Date{
    var time = this.time

    time += when(units){
        TimeUnits.SECOND->value * SECOND
        TimeUnits.MINUTE->value * MINUTE
        TimeUnits.HOUR->value * HOUR
        TimeUnits.DAY->value * DAY
    }
    this.time = time
    return this
}

fun Date.humanizeDiff(date: Date = Date()):String{
    var time = this.time
    var div = time - date.time
    var day = div / DAY
    var hour = div / HOUR
    var min = div / MINUTE
    var sec = div / SECOND
    // время после отправки, то есть когда указывается отрицательно значение
    if(div < 0){
        day *= -1
        hour *= -1
        min *= -1
        sec *= -1
        when{
            sec in 0..1 -> return "только что"
            sec in 2..45 -> return "несколько секунд назад"
            sec in 46..75 -> return "минуту назад"
            sec > 75 && min <= 45 -> when {
                min % 10 == 1L -> return "$min минуту назад"
                min % 10 in 2..4 -> return "$min минуты назад"
                min % 10 >= 5L -> return "$min минут назад"
            }
//            return "$min минут назад"
            min in 46..75 -> return "час назад"
            min > 75 && hour <= 22 -> when{
                hour % 10 == 1L -> return "$hour час назад"
                hour % 10 in 2..4 -> return "$hour часа назад"
                hour % 10 >= 5L -> return "$hour часов назад"
            }
//                return "$hour часов назад"
            hour in 23..26 -> return "день назад"
            hour > 26 && day <= 360 -> when{
                day % 10 == 1L -> return "$day день назад"
                day % 10 in 2..4 -> return "$day дня назад"
                day % 10 >= 5L -> return "$day дней назад"
            }
//                return "$day дней назад"
            day > 360 -> return "более года назад"
        }
    }
    // время до отправки, то есть когда указывается положительное значение
    else if(div > 0){

        when{
            sec in 0..1 -> return "только что"
            sec in 2..45 -> return "через несколько секунд"
            sec in 46..75 -> return "через минуту"
            sec > 75 && min <= 45 -> when {
                min % 10 == 1L -> return "через $min минуту"
                min % 10 in 2..4 -> return "через $min минуты"
                min % 10 >= 5L -> return "через $min минут"
            }
//            return "$min минут назад"
            min in 46..75 -> return "через час"
            min > 75 && hour <= 22 -> when{
                hour % 10 == 1L -> return "через $hour час"
                hour % 10 in 2..4 -> return "через $hour часа"
                hour % 10 >= 5L -> return "через $hour часов"
            }
//                return "$hour часов назад"
            hour in 23..26 -> return "через день"
            hour > 26 && day <= 360 -> when{
                day % 10 == 1L -> return "через $day день"
                day % 10 in 2..4 -> return "через $day дня"
                day % 10 >= 5L -> return "через $day дней"
            }
//                return "$day дней назад"
            day > 360 -> return "более чем через год"
        }
    }

    return "только что"
}

enum class TimeUnits{
    SECOND,
    MINUTE,
    HOUR,
    DAY;

}

fun TimeUnits.plural(value:Int) : String{
    var str : String = ""
    if(this == TimeUnits.SECOND){
        when{
            value % 10 == 1 -> str = "$value секунду"
            value % 10 in 2..4 -> str = "$value секунды"
            value % 10 >= 5L -> str = "$value секунд"
        }
    }
    if(this == TimeUnits.MINUTE){
        when{
            value % 10 == 1 -> str = "$value минуту"
            value % 10 in 2..4 -> str = "$value минуты"
            value % 10 >= 5L -> str = "$value минут"
        }
    }
    if(this == TimeUnits.HOUR){
        when{
            value % 10 == 1 -> str = "$value час"
            value % 10 in 2..4 -> str = "$value часа"
            value % 10 >= 5L -> str = "$value часов"
        }
    }
    if(this == TimeUnits.DAY){
        when{
            value % 10 == 1 -> str = "$value день"
            value % 10 in 2..4 -> str = "$value дня"
            value % 10 >= 5L -> str = "$value дней"
        }
    }

    return str
}