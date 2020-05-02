package com.onappfeedback.foodappdemo

import android.content.Context
import android.graphics.Rect
import android.view.View
import androidx.recyclerview.widget.RecyclerView


class HorizontalItemDecoration(var context: Context): RecyclerView.ItemDecoration() {
    var margin_left_init: Int = 0
    var margin_right_init: Int = 0
    var margin_between: Int = 0
    init {
        margin_left_init = context.resources.getDimensionPixelSize(R.dimen.fal_horizontal_margin)
        margin_right_init = context.resources.getDimensionPixelSize(R.dimen.fal_horizontal_margin)
        margin_between = (context.resources.getDimensionPixelSize(R.dimen.fal_horizontal_margin)/2).toInt()
    }


    override fun getItemOffsets(outRect: Rect, view: View, parent: RecyclerView, state: RecyclerView.State) {
        super.getItemOffsets(outRect, view, parent, state)

        val position = parent.getChildAdapterPosition(view)
        val numberOfChilds = state.itemCount

        if(position == 0){
            outRect.left = margin_left_init
            outRect.right = margin_between
        }else if(position == (numberOfChilds - 1)){
            outRect.left = margin_between
            outRect.right = margin_right_init
        }else{
            outRect.left = margin_between
            outRect.right = margin_between
        }
    }
}