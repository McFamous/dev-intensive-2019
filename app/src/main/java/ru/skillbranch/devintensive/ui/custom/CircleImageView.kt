package ru.skillbranch.devintensive.ui.custom

import android.content.Context
import android.graphics.Canvas
import android.graphics.Paint
import android.util.AttributeSet
import androidx.annotation.ColorRes
import androidx.annotation.Dimension
import androidx.core.graphics.toColor
import ru.skillbranch.devintensive.R


class CircleImageView @JvmOverloads constructor(
    context: Context,
    attrs : AttributeSet? = null,
    defStyleAttr : Int = 0
): androidx.appcompat.widget.AppCompatImageView(context, attrs, defStyleAttr) {

    private var borderWidth = getBorderWidth()
    private var borderColor = getBorderColor()

    @Dimension
    fun getBorderWidth():Int = resources.getDimension(R.dimen.cv_borderWidth).toInt()

    fun setBorderWidth(@Dimension dp:Int){
        if(getBorderWidth() == dp)
            return
        borderWidth = dp
    }

    private fun getBorderColor():Int = resources.getColor(R.color.cv_borderColor)

    fun setBorderColor(hex:String){
        getBorderColor()
    }

    fun setBorderColor(@ColorRes colorId: Int){
        if(getBorderColor() == colorId)
            return
        borderColor = colorId
    }
    override fun onDraw(canvas: Canvas?) {
        var backgroundPaint : Paint = Paint()
        backgroundPaint.color = borderColor
        canvas?.drawCircle(width/2.toFloat(),
            height/2.toFloat(),
            R.dimen.avatar_round_size.toFloat(),
            backgroundPaint)

    }
}