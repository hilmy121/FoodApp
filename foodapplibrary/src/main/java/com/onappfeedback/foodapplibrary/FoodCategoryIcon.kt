package com.onappfeedback.foodapplibrary

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.ImageView
import android.widget.RelativeLayout
import android.widget.TextView
import androidx.core.content.ContextCompat

class FoodCategoryIcon: RelativeLayout {

    //Controls
    var imageIconHolder : ImageView
    var textCategory: TextView
    var iconReference = 0
    var defaultDescription = ""

    init {
        val inflater = context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
        val rootView = inflater.inflate(R.layout.parcial_food_category,this,true)

        imageIconHolder = rootView.findViewById(R.id.image_category_icon) as ImageView
        textCategory = rootView.findViewById(R.id.text_category_description) as TextView
    }

    constructor(context: Context): this(context,null)
    constructor(context: Context, attributeSet: AttributeSet?): this(context,attributeSet,0)
    constructor(context: Context, attributeSet: AttributeSet?, defStyleAttr: Int): super(context,attributeSet,defStyleAttr){

        val attributes = context.obtainStyledAttributes(attributeSet,R.styleable.FoodCategoryIcon)
        val N = attributes.indexCount

        for( i in 0 until N ){
            val attr = attributes.getIndex(i)
            when(attr){
                R.styleable.FoodCategoryIcon_food_icon_src -> {
                    iconReference = attributes.getResourceId(attr,R.drawable.fal_image_placeholder)
                }
                R.styleable.FoodCategoryIcon_food_category_description -> {
                    defaultDescription = attributes.getString(attr) ?: ""
                }
            }
        }
        attributes.recycle()

    }

    override fun onFinishInflate() {
        super.onFinishInflate()

        if(iconReference!=0)
            imageIconHolder.setImageDrawable(ContextCompat.getDrawable(context,iconReference))

        if(defaultDescription != "")
            textCategory.text = defaultDescription
    }
}