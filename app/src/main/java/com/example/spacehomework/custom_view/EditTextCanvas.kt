package com.example.spacehomework.custom_view

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.RectF
import android.graphics.drawable.Drawable
import android.text.InputType
import android.util.AttributeSet
import androidx.appcompat.widget.AppCompatEditText
import com.example.spacehomework.R

class EditTextCanvas @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : AppCompatEditText(context, attrs, defStyleAttr) {

    private var title: String? = null
    private var bottomMessage: String? = null
    private var icon: Drawable? = null

    private val borderPaint = Paint(Paint.ANTI_ALIAS_FLAG).apply {
        style = Paint.Style.STROKE
        color = Color.BLACK
        strokeWidth = resources.getDimensionPixelSize(R.dimen.border_width).toFloat()
    }

    private val rect = RectF()

    init {
        // retrieve custom attributes
        val typedArray = context.obtainStyledAttributes(
            attrs,
            R.styleable.CustomEditText,
            defStyleAttr,
            0
        )
        title = typedArray.getString(R.styleable.CustomEditText_title)
        bottomMessage = typedArray.getString(R.styleable.CustomEditText_bottomMessage)
        icon = typedArray.getDrawable(R.styleable.CustomEditText_icon)

        // set title and bottom message as hint
        hint = title?.let {
            val builder = StringBuilder()
            builder.append(it)
            bottomMessage?.let { message ->
                builder.append("\n")
                builder.append(message)
            }
            builder.toString()
        }

        // set icon
        icon?.let {
            setCompoundDrawablesRelativeWithIntrinsicBounds(null, null, it, null)
        }

        // enable text input
        inputType = InputType.TYPE_CLASS_TEXT

        // recycle the typed array
        typedArray.recycle()
    }

    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)

        // calculate border rectangle
        rect.set(
            borderPaint.strokeWidth / 2,
            borderPaint.strokeWidth / 2,
            width - borderPaint.strokeWidth / 2,
            height - borderPaint.strokeWidth / 2
        )

        // draw border
        canvas?.drawRect(rect, borderPaint)

        // draw title
        title?.let {
            val titlePaint = paint
            titlePaint.color = currentHintTextColor
            titlePaint.textSize = textSize / 2

            canvas?.drawText(
                it,
                borderPaint.strokeWidth,
                titlePaint.fontSpacing,
                titlePaint
            )
        }

        // draw bottom message
        bottomMessage?.let {
            val messagePaint = paint
            messagePaint.color = currentHintTextColor
            messagePaint.textSize = textSize / 2

            canvas?.drawText(
                it,
                borderPaint.strokeWidth,
                height - messagePaint.fontMetrics.bottom,
                messagePaint
            )
        }
    }

    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec)

        // add border width to measured size
        val borderSize = resources.getDimensionPixelSize(R.dimen.border_width)
        val width = measuredWidth + borderSize * 2
        val height = measuredHeight + borderSize * 2
        setMeasuredDimension(width, height)
    }
}
