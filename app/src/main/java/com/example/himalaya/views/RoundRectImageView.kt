package com.example.himalaya.views

import android.content.Context
import android.graphics.Canvas
import android.graphics.Path
import android.graphics.RectF
import android.util.AttributeSet
import androidx.appcompat.widget.AppCompatImageView


class RoundRectImageView(context:Context,attrs: AttributeSet?):AppCompatImageView(context,attrs) {
    private val roundRatio = 0.1f

    private var path: Path? = null

    override fun onDraw(canvas: Canvas) {
        if (path == null) {
            path = Path()
            path!!.addRoundRect(
                RectF(0F, 0F, width.toFloat(), height.toFloat()),
                roundRatio * width,
                roundRatio * height,
                Path.Direction.CW
            )
        }
        canvas.save()
        canvas.clipPath(path!!)
        super.onDraw(canvas)
        canvas.restore()
    }
}