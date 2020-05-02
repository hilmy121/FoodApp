package com.onappfeedback.foodappdemo

import android.graphics.Bitmap
import android.graphics.drawable.Drawable
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.onappfeedback.foodapplibrary.FoodItem
import com.onappfeedback.foodapplibrary.FoodTile
import com.squareup.picasso.Picasso

class ImagesFragment: Fragment() {

    var tileDemo : FoodTile? = null
    var tileDemo2 : FoodTile? = null
    var foodItem : FoodItem? = null
    var foodItem2 : FoodItem? = null

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val rootView = inflater.inflate(R.layout.fragment_images,container,false)

        tileDemo = rootView.findViewById(R.id.tile_demo1)
        tileDemo2 = rootView.findViewById(R.id.tile_demo2)
        foodItem = rootView.findViewById(R.id.fooditem_demo1)
        foodItem2 = rootView.findViewById(R.id.fooditem_demo2)

        return rootView
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        //Tile From Src
        tileDemo?.setImageResource(R.drawable.chicken_burger_with_fries_image)
        tileDemo?.setTitle("Title added by programming")


        //Tile From Bitmap
        val loadIntoTile2 = object: com.squareup.picasso.Target {
            override fun onBitmapFailed(e: java.lang.Exception?, errorDrawable: Drawable?) {
                Toast.makeText(requireContext(),"Failed to load Image",Toast.LENGTH_SHORT).show()
            }

            override fun onPrepareLoad(placeHolderDrawable: Drawable?) {
                tileDemo2?.setImageResource(R.drawable.fal_image_placeholder)
            }

            override fun onBitmapLoaded(bitmap: Bitmap?, from: Picasso.LoadedFrom?) {
                if(bitmap!= null) tileDemo2?.setImageBitmap(bitmap)



            }
        }
        Picasso.get().load("https://images.pexels.com/photos/1647121/pexels-photo-1647121.jpeg?auto=compress&cs=tinysrgb&h=250&w=420").into(loadIntoTile2)
        tileDemo2?.setTitle("Title added by programming")

        //Food Item From Src
        foodItem?.setImageResource(R.drawable.hamburger_with_ham_image)
        foodItem?.setTitle("Title added by programming")
        foodItem?.setCategory("Default Category")
        foodItem?.setPrice("$ 1.00")
        foodItem?.setRating("0 ratings")

        //Food Item From Bitmap
        val loadIntoFoodItem2 = object: com.squareup.picasso.Target {
            override fun onBitmapFailed(e: java.lang.Exception?, errorDrawable: Drawable?) {
                Toast.makeText(requireContext(),"Failed to load Image",Toast.LENGTH_SHORT).show()
            }

            override fun onPrepareLoad(placeHolderDrawable: Drawable?) {
                foodItem2?.setImageResource(R.drawable.fal_image_placeholder)
            }

            override fun onBitmapLoaded(bitmap: Bitmap?, from: Picasso.LoadedFrom?) {
                if(bitmap!= null) foodItem2?.setImageBitmap(bitmap)



            }
        }
        Picasso.get().load("https://images.pexels.com/photos/793166/pexels-photo-793166.jpeg?auto=compress&cs=tinysrgb&h=250&w=420").into(loadIntoFoodItem2)
        foodItem2?.setTitle("Title added by programming")
        foodItem2?.setCategory("Default Category")
        foodItem2?.setPrice("$ 1.00")
        foodItem2?.setRating("0 ratings")









    }
}