package com.onappfeedback.foodapplibrary

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.ImageView
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout

class Counter: ConstraintLayout {

    //Controls
    private var textLabel : TextView
    private var buttonIncrease : ImageView
    private var buttonDecrease : ImageView
    private var textCurrentValue : TextView
    private var currentValue = 0

    //default values
    var defaultLabel = ""



    constructor(context: Context): this(context,null)
    constructor(context: Context, attributeSet: AttributeSet?): this(context,attributeSet,0)
    constructor(context: Context, attributeSet: AttributeSet?, defStyleAttr: Int): super(context,attributeSet,0){

        val attributes = context.obtainStyledAttributes(attributeSet,R.styleable.Counter)
        val N = attributes.indexCount

        for( i in 0 until N ){
            val attr = attributes.getIndex(i)
            when(attr){
                R.styleable.Counter_label -> {
                    defaultLabel = attributes.getString(attr) ?: ""
                }
            }
        }
        attributes.recycle()

    }

    init {
        val inflater = context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
        val rootView = inflater.inflate(R.layout.parcial_counter,this,true)
        textLabel = rootView.findViewById(R.id.text_counter_label)
        buttonIncrease = rootView.findViewById(R.id.button_plus)
        buttonDecrease = rootView.findViewById(R.id.button_minus)
        textCurrentValue = rootView.findViewById(R.id.text_counter)

        updateDisplayedValue()

        buttonIncrease.setOnClickListener {
            currentValue++
            updateDisplayedValue()
        }
        buttonDecrease.setOnClickListener {
            if (currentValue != 0)
                currentValue--
            updateDisplayedValue()
        }
    }

    override fun onFinishInflate() {
        super.onFinishInflate()

        textLabel.text = defaultLabel
    }

    private fun updateDisplayedValue(){
        textCurrentValue.text = currentValue.toString()
    }

    fun setCounter(value: Int = 0){
        currentValue = value
        updateDisplayedValue()

    }

    fun getCounter() = this.currentValue

}