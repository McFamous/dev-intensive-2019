package ru.skillbranch.devintensive.extensions


fun String.truncate(len : Int = 16):String{
    var str = this.trim()
    if(str.length > len){
        str = str.substring(0,len)
        str = str.trim()
        str += "..."
    }
    return str
}


fun String.stripHtml() : String{

    return this
        .substring(this.indexOf('>') + 1,this.lastIndexOf('<'))
        .replace("\\s+".toRegex(), " ")
}