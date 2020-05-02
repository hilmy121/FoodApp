package com.onappfeedback.foodapplibrary

import android.content.Context
import android.graphics.drawable.AnimatedVectorDrawable
import android.os.Build
import android.util.AttributeSet
import android.widget.ImageView
import androidx.core.content.ContextCompat
import androidx.vectordrawable.graphics.drawable.AnimatedVectorDrawableCompat

class FavoriteToggleButton : ImageView {

    var isChecked: Boolean = false
        get
        set(value) {
            field = value
            if(value){
                if (Build.VERSION.SDK_INT < 21) {
                    val bordless_to_fill =
                        ContextCompat.getDrawable(context, R.drawable.fal_heart_animation_reverse) as AnimatedVectorDrawableCompat
                    setImageDrawable(bordless_to_fill)
                } else {
                    val bordless_to_fill =
                        ContextCompat.getDrawable(context, R.drawable.fal_heart_animation_reverse) as AnimatedVectorDrawable
                    setImageDrawable(bordless_to_fill)
                }
            }else{
                if (Build.VERSION.SDK_INT < 21) {
                    val bordless_to_fill =
                        ContextCompat.getDrawable(context, R.drawable.fal_heart_animation) as AnimatedVectorDrawableCompat
                    setImageDrawable(bordless_to_fill)
                } else {
                    val bordless_to_fill =
                        ContextCompat.getDrawable(context, R.drawable.fal_heart_animation) as AnimatedVectorDrawable
                    setImageDrawable(bordless_to_fill)
                }
            }
        }
    private var listener: OnToggleListener? = null

    constructor(context: Context): this(context,null)
    constructor(context: Context,attributeSet: AttributeSet?): this(context,attributeSet,0)
    constructor(context: Context,attributeSet: AttributeSet?, defStyleAttr: Int): super(context,attributeSet,defStyleAttr)

    init {
        isChecked = false

        if (Build.VERSION.SDK_INT < 21) {
            val bordless_to_fill =
                ContextCompat.getDrawable(context, R.drawable.fal_heart_animation) as AnimatedVectorDrawableCompat
            setImageDrawable(bordless_to_fill)
        } else {
            val bordless_to_fill =
                ContextCompat.getDrawable(context, R.drawable.fal_heart_animation) as AnimatedVectorDrawable
            setImageDrawable(bordless_to_fill)
        }

    }



    override fun onFinishInflate() {
        super.onFinishInflate()

        this.setOnClickListener {
            morph()
        }
    }

    fun morph() {

        if (Build.VERSION.SDK_INT < 21) {
            val bordless_to_fill =
                ContextCompat.getDrawable(context, R.drawable.fal_heart_animation) as AnimatedVectorDrawableCompat
            val fill_to_bordless = ContextCompat.getDrawable(
                context,
                R.drawable.fal_heart_animation_reverse
            ) as AnimatedVectorDrawableCompat

            val drawable = if (isChecked) fill_to_bordless else bordless_to_fill
            setImageDrawable(drawable)
            drawable.start()
        } else {
            val bordless_to_fill =
                ContextCompat.getDrawable(context, R.drawable.fal_heart_animation) as AnimatedVectorDrawable
            val fill_to_bordless = ContextCompat.getDrawable(
                context,
                R.drawable.fal_heart_animation_reverse
            ) as AnimatedVectorDrawable

            val drawable = if (isChecked) fill_to_bordless else bordless_to_fill
            setImageDrawable(drawable)
            drawable.start()
        }

        isChecked = !isChecked
        listener?.onToggle(this,isChecked)
    }

    fun setOnToggleListener(listener: OnToggleListener?) {
        this.listener = listener
    }

    fun setOnToggleListener(listener: (FavoriteToggleButton,Boolean) -> Unit){
        this.listener = object : OnToggleListener{
            override fun onToggle(toggleView: FavoriteToggleButton, isChecked: Boolean) {
                listener(toggleView,isChecked)
            }
        }
    }


}