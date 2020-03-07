package ru.skillbranch.devintensive.utils

object Utils {
    fun parseFullName(fullName:String?):Pair<String?, String?>{

        val parts: List<String>? = fullName?.split(" ")
        var firstName:String? = parts?.getOrNull(0)
        var lastName:String? = parts?.getOrNull(1)

        if(parts?.getOrNull(0)?.length == 0) firstName = null
        if(parts?.getOrNull(1)?.length == 0 ) lastName = null


        return firstName to lastName
    }

    fun transliteration(payload: String, divider: String = " "): String {

        return payload
            .replace('а', 'a')
            .replace('б', 'b')
            .replace('в', 'v')
            .replace('г', 'g')
            .replace('д', 'd')
            .replace('е', 'e')
            .replace('ё', 'e')
            .replace("ж", "zh")
            .replace('з', 'z')
            .replace('и', 'i')
            .replace('й', 'i')
            .replace('к', 'k')
            .replace('л', 'l')
            .replace('м', 'm')
            .replace('н', 'n')
            .replace('о', 'o')
            .replace('п', 'p')
            .replace('р', 'r')
            .replace('с', 's')
            .replace('т', 't')
            .replace('у', 'u')
            .replace('ф', 'f')
            .replace('х', 'h')
            .replace('ц', 'c')
            .replace("ч", "ch")
            .replace("ш", "sh")
            .replace("щ", "sh")
            .replace("ъ", "")
            .replace('ы', 'i')
            .replace("ь", "")
            .replace('э', 'e')
            .replace("ю", "yu")
            .replace("я", "ya")

            .replace('А', 'A')
            .replace('Б', 'B')
            .replace('В', 'V')
            .replace('Г', 'G')
            .replace('Д', 'D')
            .replace('Е', 'E')
            .replace('Ё', 'E')
            .replace("Ж", "Zh")
            .replace('З', 'Z')
            .replace('И', 'I')
            .replace('Й', 'I')
            .replace('К', 'K')
            .replace('Л', 'L')
            .replace('М', 'M')
            .replace('Н', 'N')
            .replace('О', 'O')
            .replace('П', 'P')
            .replace('Р', 'R')
            .replace('С', 'S')
            .replace('Т', 'T')
            .replace('У', 'U')
            .replace('Ф', 'F')
            .replace('Х', 'H')
            .replace('Ц', 'C')
            .replace("Ч", "Ch")
            .replace("Ш", "Sh")
            .replace("Щ", "Sh")
            .replace("Ъ", "")
            .replace('Ы', 'i')
            .replace("Ь", "")
            .replace('Э', 'E')
            .replace("Ю", "Yu")
            .replace("Я", "Ya")
            .replace(" ", divider)
    }

    fun toInitials(firstName: String?, lastName: String?): String? {
        var str =
            if(firstName == null || firstName == " " || firstName == "")
                ""
            else
                firstName.substring(0,1)
        str +=
            if(lastName == null || lastName == " " || lastName == "")
                ""
            else
                lastName.substring(0,1)
        if(str == "")
            return null

        return str?.toUpperCase()
    }
}