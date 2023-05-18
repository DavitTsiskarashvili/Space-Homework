package com.example.spacehomework.custom_view

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.util.AttributeSet
import android.view.View
import kotlin.math.min

class BatteryIconView(context: Context, attrs: AttributeSet) : View(context, attrs) {

    private val batteryWidth = 300f
    private val batteryHeight = 150f
    private val batteryBorderWidth = 5f
    private val batteryCornerRadius = 15f
    private val smallRectanglePadding = 8f
    private val smallRectangleWidth = 20f
    private val smallRectangleHeight = 80f
    private val paddingEnd = 10f

    private var batteryPercent = 80
    private val greenWidth =
        min(batteryWidth * (batteryPercent.toFloat() / 100), batteryWidth) - paddingEnd

    private val greenPaint = Paint().apply {
        style = Paint.Style.FILL
        color = Color.GREEN
    }

    private val borderPaint = Paint().apply {
        style = Paint.Style.STROKE
        color = Color.BLACK
        strokeWidth = 5f
    }

    private val textPaint = Paint().apply {
        style = Paint.Style.FILL
        color = Color.BLACK
        textSize = 20f
        textAlign = Paint.Align.CENTER
    }

    private val smallRectanglePaint = Paint().apply {
        style = Paint.Style.FILL
        color = Color.GRAY
    }


    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)

        // shape of battery icon
        canvas.drawRoundRect(
            0f,
            0f,
            batteryWidth,
            batteryHeight,
            batteryCornerRadius,
            batteryCornerRadius,
            borderPaint
        )

        // battery percentage with green fill
        canvas.drawRoundRect(
            batteryBorderWidth,
            batteryBorderWidth,
            batteryBorderWidth + greenWidth,
            batteryHeight - batteryBorderWidth,
            batteryCornerRadius,
            batteryCornerRadius,
            greenPaint
        )

        // small rectangle in the beginning
        canvas.drawRect(
            batteryWidth - batteryBorderWidth + smallRectanglePadding,
            (batteryHeight - smallRectangleHeight) / 2f,
            batteryWidth - batteryBorderWidth + smallRectangleWidth,
            (batteryHeight - smallRectangleHeight) / 2f + smallRectangleHeight,
            smallRectanglePaint
        )

        // battery percentage
        canvas.drawText(
            "$batteryPercent%",
            batteryWidth / 2,
            batteryHeight / 2 + textPaint.textSize / 2,
            textPaint
        )
    }

}
