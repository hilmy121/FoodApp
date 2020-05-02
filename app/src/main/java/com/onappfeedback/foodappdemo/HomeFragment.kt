package com.onappfeedback.foodappdemo
import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.onappfeedback.foodapplibrary.FavoriteToggleButton
import com.onappfeedback.foodapplibrary.FoodCategoryIcon
import com.onappfeedback.foodapplibrary.OnToggleListener

class HomeFragment : Fragment() {

    var rvTiles : RecyclerView? = null
    var rvList : RecyclerView? = null
    var foodCategoryIcon: FoodCategoryIcon? = null

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val rootView = inflater.inflate(R.layout.fragment_home,container,false)

        rvTiles  = rootView.findViewById(R.id.rv_food_tiles)
        rvList = rootView.findViewById(R.id.rv_food)
        foodCategoryIcon = rootView.findViewById(R.id.btn_category_free_delivery)

        return rootView
    }

    @SuppressLint("WrongConstant")
    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        foodCategoryIcon?.setOnClickListener {
            Toast.makeText(requireContext(),"Free Delivery Pressed",Toast.LENGTH_SHORT).show()
        }

        // Tiles
        rvTiles?.layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL,false)
        rvTiles?.addItemDecoration(HorizontalItemDecoration(requireContext()))

        val foodItemsTiles: MutableList<FoodTileData> = mutableListOf()
        val tilesAdapter = FoodTilesAdapter(foodItemsTiles)
        rvTiles?.adapter = tilesAdapter


        val tilesImageArray = arrayListOf<Int>(R.drawable.south_indian_foods_image,R.drawable.spicy_burger_image,R.drawable.fast_food_image)
        for(i in 0..8){
            foodItemsTiles.add(
                FoodTileData(
                    tilesImageArray[i%3],
                    "Food ${i + 1}"
                )
            )

        }
        tilesAdapter.notifyDataSetChanged()


        //List
        rvList?.layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL,false)

        val foodItems: MutableList<FoodDataItem> = mutableListOf()
        val adapter = FoodListAdapter(foodItems,object: OnToggleListener {
            override fun onToggle(toggleView: FavoriteToggleButton, isChecked: Boolean) {
                if(isChecked) Toast.makeText(context,"added to favorites", Toast.LENGTH_SHORT).show()
                else Toast.makeText(context,"I don't like it anymore.", Toast.LENGTH_SHORT).show()
            }
        })
        rvList?.adapter = adapter
        rvList?.addItemDecoration(FoodListItemDecoration(requireContext()))

        val listImageArray = arrayListOf<Int>(R.drawable.vegan_food_swaps_image,R.drawable.new_dessert_slice_image,R.drawable.lemon_juices)

        for(i in 0..9){
            foodItems.add(
                FoodDataItem(
                    listImageArray[i%3],
                    "Food ${i+1}",
                    "Category Default",
                    "$1.00",
                    "$i ratings")
            )

        }
        adapter.notifyDataSetChanged()


    }

}