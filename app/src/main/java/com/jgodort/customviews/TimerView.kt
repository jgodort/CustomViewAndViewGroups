package com.jgodort.customviews

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.text.TextPaint
import android.util.AttributeSet
import android.view.View
import androidx.core.content.ContextCompat

class TimerView : View {
    private val backgroundPaint = Paint().apply {
        color = Color.parseColor("#880E4F")
    }

    private val numberPaint = TextPaint(Paint.ANTI_ALIAS_FLAG).apply {
        color = ContextCompat.getColor(context, android.R.color.white)
        textSize = 64f * resources.displayMetrics.scaledDensity
    }

    private var startTime = 0L

    private val maxSeconds = 99999

    private val updateRunnable = Runnable {
        updateTimer()
    }

    constructor(context: Context?) : super(context)
    constructor(
        context: Context?,
        attrs: AttributeSet?
    ) : super(context, attrs)

    constructor(
        context: Context?,
        attrs: AttributeSet?,
        defStyleAttr: Int
    ) : super(context, attrs, defStyleAttr)

    constructor(
        context: Context?,
        attrs: AttributeSet?,
        defStyleAttr: Int,
        defStyleRes: Int
    ) : super(context, attrs, defStyleAttr, defStyleRes)

    fun start() {
        startTime = System.currentTimeMillis()
        updateTimer()
    }

    fun stop() {
        startTime = 0
        removeCallbacks(updateRunnable)
    }

    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {

        val fontMetrics = numberPaint.fontMetrics
        val maxTextWidth = Math.ceil(numberPaint.measureText(maxSeconds.toString()).toDouble()).toInt()
        val maxTextHeight = Math.ceil((-fontMetrics.top + fontMetrics.bottom).toDouble()).toInt()

        val contentWidth = maxTextWidth + paddingLeft + paddingRight
        val contentHeight = maxTextHeight + paddingTop + paddingBottom

        val contentSize = Math.max(contentWidth, contentHeight)

        val measuredWidth = resolveSize(contentSize, widthMeasureSpec)
        val measuredHeight = resolveSize(contentSize, heightMeasureSpec)

        setMeasuredDimension(measuredWidth, measuredHeight)

    }

    override fun onDraw(canvas: Canvas) {
        val canvasWidth = canvas.width
        val canvasHeight = canvas.height

        val centerX = Math.round(canvasWidth * 0.5f)
            .toFloat()
        val centerY = Math.round(canvasHeight * 0.5f)
            .toFloat()
        val radius = (if (canvasWidth < canvasHeight) canvasWidth else canvasHeight) * 0.5f

        //val number = ((System.currentTimeMillis() - startTime) * 0.001).toInt().toString()
        val number = maxSeconds.toString()
        val textOffsetX = numberPaint.measureText(number) * 0.5f
        val textOffsetY = numberPaint.fontMetrics.ascent * -0.4f

        canvas.apply {
            drawCircle(centerX, centerY, radius, backgroundPaint)
            drawText(number, centerX - textOffsetX, centerY + textOffsetY, numberPaint)
        }

    }

    private fun updateTimer() {
        invalidate()
        postDelayed(updateRunnable, 200L)
    }

}