package com.onappfeedback.foodappdemo

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.onappfeedback.foodapplibrary.FoodTile

class FoodTilesAdapter(var foodItems: MutableList<FoodTileData>) : RecyclerView.Adapter<FoodTilesAdapter.FoodTileHolder>(){
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FoodTileHolder {
        val foodTileItem = FoodTile(parent.context)
        return FoodTileHolder(foodTileItem)
    }

    override fun getItemCount(): Int {
        return foodItems.count()
    }

    override fun onBindViewHolder(holder: FoodTileHolder, position: Int) {
        holder.bindData(foodItems.get(position))
    }

    class FoodTileHolder(val foodTile: FoodTile) : RecyclerView.ViewHolder(foodTile){
        fun bindData(data: FoodTileData){
            if(data.resource != 0)
                foodTile.setImageResource(data.resource)

            foodTile.setTitle(data.title)
        }
    }
}