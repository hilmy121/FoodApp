package com.onappfeedback.foodappdemo

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.onappfeedback.foodapplibrary.FoodItem
import com.onappfeedback.foodapplibrary.OnToggleListener

class FoodListAdapter(var foodItems: MutableList<FoodDataItem>,var onToggleListener: OnToggleListener? = null) : RecyclerView.Adapter<FoodListAdapter.FoodHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FoodHolder {
        val foodItem = FoodItem(parent.context)
        return FoodHolder(foodItem)
    }

    override fun getItemCount(): Int {
        return foodItems.count()
    }

    override fun onBindViewHolder(holder: FoodHolder, position: Int) {
        holder.bindData(foodItems.get(position),onToggleListener)
    }

    class FoodHolder(val foodItem: FoodItem) : RecyclerView.ViewHolder(foodItem) {
        fun bindData(data: FoodDataItem,onToggleListener: OnToggleListener? = null){
            if(data.imageResourceId != 0)
                foodItem.setImageResource(data.imageResourceId)

            foodItem.setTitle(data.title)
            foodItem.setCategory(data.category)
            foodItem.setPrice(data.price)
            foodItem.setRating(data.rating)

            foodItem.buttonFavorite?.isChecked = data.bookmarked

            foodItem.buttonFavorite?.setOnToggleListener { favoriteToggleButton, b ->
                data.bookmarked = b
                onToggleListener?.onToggle(favoriteToggleButton,b)
            }
        }

    }
}