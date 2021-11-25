package com.example.himalaya.views

import android.annotation.SuppressLint
import android.content.Context
import android.graphics.Canvas
import android.util.AttributeSet
import android.widget.ImageView
import com.example.himalaya.R

@SuppressLint("AppCompatCustomView")
class LoadingView : ImageView{

    private var rotateDegree = 0.0f

    private var mNeedRotate=true

    constructor(context: Context):this(context,null)

    constructor(context: Context, attrs: AttributeSet?):this(context,attrs,0)

    constructor(context: Context,attrs:AttributeSet?,defStyleAttr:Int):super(context,attrs,defStyleAttr){
        setImageResource(R.drawable.ic_data_loading)

    }

    override fun onAttachedToWindow() {
        super.onAttachedToWindow()
        post(object :Runnable{
            override fun run() {
                    rotateDegree+=30
                    rotateDegree=if (rotateDegree==360f) 0f else rotateDegree
                    invalidate()
                    if (mNeedRotate){
                        postDelayed(this,100)
                    }
            }
        })
    }

    override fun onDetachedFromWindow() {
        super.onDetachedFromWindow()
        mNeedRotate=false
    }

    override fun onDraw(canvas: Canvas?) {
        canvas?.rotate(rotateDegree, (width/2).toFloat(), (height/2).toFloat())
        super.onDraw(canvas)

    }
}