package com.example.seatselectionmodule

import android.content.Context
import android.support.v4.widget.TextViewCompat
import android.util.AttributeSet
import android.view.Gravity
import android.view.View
import android.widget.FrameLayout
import android.widget.ImageView
import android.widget.TextView

class SeatView(context: Context, attrs: AttributeSet?) : FrameLayout(context, attrs) {

    var requiredPaymentType1 = false
        set(value) {
            if (value != field) {
                field = value
                refreshDrawableState()
                textView.visibility = if (value) View.VISIBLE else View.INVISIBLE
            }
        }

    var requiredPaymentType2 = false
        set(value) {
            if (value != field) {
                field = value
                refreshDrawableState()
                textView.visibility = if (value) View.VISIBLE else View.INVISIBLE
            }
        }

    var imageResource: Int = 0
        set(value) {
            field = value
            if (value != 0) {
                isSelected = true
                icon.setImageResource(value)
                icon.visibility = View.VISIBLE
            } else {
                isSelected = false
                icon.visibility = View.INVISIBLE
            }
        }

    private val icon: ImageView = ImageView(context)
    private val textView: TextView = TextView(context)

    init {
        TextViewCompat.setTextAppearance(textView, R.style.TextAppearanceThinWhite42)
        val layoutParamsTV = LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT)
        layoutParamsTV.gravity = Gravity.CENTER
        textView.layoutParams = layoutParamsTV
        textView.text = "\u20BD"
        textView.visibility = View.INVISIBLE

        val layoutParamsIcon = LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT)
        layoutParamsIcon.gravity = Gravity.BOTTOM or Gravity.CENTER_HORIZONTAL
        layoutParamsIcon.bottomMargin = resources.getDimension(R.dimen.seat_view_icon_margin_bottom).toInt()
        icon.layoutParams = layoutParamsIcon
        icon.scaleType = ImageView.ScaleType.CENTER_INSIDE

        val a = context.obtainStyledAttributes(attrs, R.styleable.SeatView)
        requiredPaymentType1 = a.getBoolean(R.styleable.SeatView_state_required_payment_type_1, false)
        requiredPaymentType2 = a.getBoolean(R.styleable.SeatView_state_required_payment_type_2, false)
        imageResource = a.getResourceId(R.styleable.SeatView_imageResource, 0)

        a.recycle()

        addView(textView)
        addView(icon)
    }

    override fun onCreateDrawableState(extraSpace: Int): IntArray {
        val drawableState = super.onCreateDrawableState(extraSpace + 2)

        if (requiredPaymentType1)
            View.mergeDrawableStates(drawableState, STATE_REQUIRED_PAYMENT_TYPE_1)

        if (requiredPaymentType2)
            View.mergeDrawableStates(drawableState, STATE_REQUIRED_PAYMENT_TYPE_2)

        return drawableState
    }

    companion object {
        private val STATE_REQUIRED_PAYMENT_TYPE_1 = intArrayOf(R.attr.state_required_payment_type_1)
        private val STATE_REQUIRED_PAYMENT_TYPE_2 = intArrayOf(R.attr.state_required_payment_type_2)
    }
}