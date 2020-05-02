package com.onappfeedback.foodapplibrary

import android.content.Context
import android.graphics.Bitmap
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.RelativeLayout
import android.widget.TextView
import androidx.core.content.ContextCompat
import com.makeramen.roundedimageview.RoundedImageView

class FoodItem : RelativeLayout {

    //Controls
    var imageFoodItem : RoundedImageView? = null
    var imageReference: Int  = 0
    var buttonFavorite: FavoriteToggleButton? = null

    var textFoodTitle: TextView? = null
    var textFoodCategory: TextView? = null
    var textFoodPrice: TextView? = null
    var textFoodRating: TextView? = null

    var defaultTitle = ""
    var defaultCategory = ""
    var defaultPrice = ""
    var defaultRating = ""

    init {
        val inflater = context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
        val rootView = inflater.inflate(R.layout.parcial_food_list_item,this,true)

        imageFoodItem = rootView.findViewById(R.id.image_food) as RoundedImageView
        buttonFavorite = rootView.findViewById(R.id.button_food_favorite) as FavoriteToggleButton

        textFoodTitle = rootView.findViewById(R.id.text_food_title) as TextView
        textFoodCategory = rootView.findViewById(R.id.text_food_category) as TextView
        textFoodPrice = rootView.findViewById(R.id.text_food_price) as TextView
        textFoodRating = rootView.findViewById(R.id.text_food_rating) as TextView
    }

    constructor(context: Context): this(context,null)
    constructor(context: Context,attributeSet: AttributeSet?): this(context,attributeSet,0)
    constructor(context: Context,attributeSet: AttributeSet?, defStyleAttr: Int): super(context,attributeSet,defStyleAttr){

        val attributes = context.obtainStyledAttributes(attributeSet,R.styleable.FoodItem)
        val N = attributes.indexCount

        for( i in 0 until N ){
            val attr = attributes.getIndex(i)
            when(attr){
                R.styleable.FoodItem_food_image_src -> {
                    imageReference = attributes.getResourceId(attr,R.drawable.fal_image_placeholder)
                }
                R.styleable.FoodItem_food_title -> {
                    defaultTitle = attributes.getString(attr) ?: ""
                }
                R.styleable.FoodItem_food_category -> {
                    defaultCategory = attributes.getString(attr) ?: ""
                }
                R.styleable.FoodItem_food_price -> {
                    defaultPrice = attributes.getString(attr) ?: ""
                }
                R.styleable.FoodItem_food_rating -> {
                    defaultRating = attributes.getString(attr) ?: ""
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

        if(defaultCategory != "")
            textFoodCategory?.text = defaultCategory

        if(defaultPrice != "")
            textFoodPrice?.text = defaultPrice

        if(defaultRating!= "")
            textFoodRating?.text = defaultRating

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

    fun setCategory(category: String){
        textFoodCategory?.setText(category)
    }

    fun setPrice(price: String){
        textFoodPrice?.setText(price)
    }

    fun setRating(rating: String){
        textFoodRating?.setText(rating)
    }

    fun setOnToggleListener(listener: OnToggleListener){
        buttonFavorite?.setOnToggleListener(listener)
    }

    fun setOnToggleListener(listener: (FavoriteToggleButton,Boolean) -> Unit){
        buttonFavorite?.setOnToggleListener(listener)
    }

}