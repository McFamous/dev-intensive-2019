package ru.skillbranch.devintensive.ui.custom

import android.content.Context
import android.util.AttributeSet
import androidx.annotation.ColorRes
import androidx.annotation.Dimension
import ru.skillbranch.devintensive.R


class CircleImageView @JvmOverloads constructor(
    context: Context,
    attrs : AttributeSet? = null,
    defStyleAttr : Int = 0
): androidx.appcompat.widget.AppCompatImageView(context, attrs, defStyleAttr) {

    @Dimension
    fun getBorderWidth() : Int = resources.getDimension(R.dimen.cv_borderWidth).toInt()

    fun setBorderWidth(@Dimension dp : Int){
    }

    fun getBorderColor() : Int = R.color.cv_borderColor

    fun setBorderColor(hex : String){

    }

    fun setBorderColor(@ColorRes colorId : Int){

    }
}