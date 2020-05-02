package com.onappfeedback.foodapplibrary

import android.content.Context
import android.graphics.Bitmap
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.RelativeLayout
import android.widget.TextView
import androidx.core.content.ContextCompat
import com.makeramen.roundedimageview.RoundedImageView

class FoodTile : RelativeLayout {

    //Controls
    var imageFoodItem : RoundedImageView? = null
    var imageReference: Int  = 0
    var defaultTitle = ""
    var textFoodTitle: TextView? = null

    init {
        val inflater = context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
        val rootView = inflater.inflate(R.layout.parcial_food_tile,this,true)

        imageFoodItem = rootView.findViewById(R.id.image_food) as RoundedImageView
        textFoodTitle = rootView.findViewById(R.id.text_food_title) as TextView

    }

    constructor(context: Context): this(context,null)
    constructor(context: Context, attributeSet: AttributeSet?): this(context,attributeSet,0)
    constructor(context: Context, attributeSet: AttributeSet?, defStyleAttr: Int): super(context,attributeSet,defStyleAttr){

        val attributes = context.obtainStyledAttributes(attributeSet,R.styleable.FoodTile)
        val N = attributes.indexCount

        for( i in 0 until N ){
            val attr = attributes.getIndex(i)
            when(attr){
                R.styleable.FoodTile_food_image_src -> {
                    imageReference = attributes.getResourceId(attr,R.drawable.fal_image_placeholder)
                }
                R.styleable.FoodTile_food_title -> {
                    defaultTitle = attributes.getString(attr) ?: ""
                }

            }
        }
        attributes.recycle()
    }

    override fun onFinishInflate() {
        super.onFinishInflate()
        if(imageReference!=0)
            imageFoodItem?.setImageDrawable(ContextCompat.getDrawable(context,imageReference))

        if(defaultTitle != "")
            textFoodTitle?.text = defaultTitle
    }

    fun setImageResource(resId: Int){
        imageFoodItem?.setImageResource(resId)
    }

    fun setImageBitmap(bitmap: Bitmap){
        imageFoodItem?.setImageBitmap(bitmap)
    }

    fun setTitle(title: String){
        textFoodTitle?.setText(title)
    }
}