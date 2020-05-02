package com.onappfeedback.foodappdemo

import android.content.Context
import android.graphics.Rect
import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.onappfeedback.foodapplibrary.fromDps

class AmountItemDecoration(context: Context): RecyclerView.ItemDecoration() {
    var verticalDivider = (14.fromDps(context)/2).toInt()


    override fun getItemOffsets(outRect: Rect, view: View, parent: RecyclerView, state: RecyclerView.State) {
        val position = parent.getChildAdapterPosition(view)
        val numberOfChilds = state.itemCount

        outRect.top = verticalDivider
        outRect.right = verticalDivider

        if(position == 0)
            outRect.top = 0
        else if(position == (numberOfChilds - 1)){
            outRect.bottom = 0
        }
    }
}