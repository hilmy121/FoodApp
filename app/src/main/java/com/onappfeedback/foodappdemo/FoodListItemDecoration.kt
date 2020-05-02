package com.onappfeedback.foodappdemo

import android.graphics.Rect
import android.view.View
import androidx.recyclerview.widget.RecyclerView
import android.graphics.Canvas
import android.graphics.Paint
import android.content.Context
import androidx.core.content.ContextCompat
import com.onappfeedback.foodapplibrary.fromDps


class FoodListItemDecoration(context: Context): RecyclerView.ItemDecoration() {
    private var paint: Paint = Paint()
    private var dividerHeight: Int = 0
    var dividerMarginRight = 0
    var dividerMarginLeft = 0
    var gutter = 0

    init {
        paint.color = ContextCompat.getColor(context,R.color.falColorDivider)
        paint.strokeWidth = 1.fromDps(context).toFloat()
        dividerHeight = 1.fromDps(context)

        dividerMarginRight = context.resources.getDimension(R.dimen.fal_horizontal_margin).toInt()
        dividerMarginLeft = context.resources.getDimension(R.dimen.fal_horizontal_margin).toInt() +
                context.resources.getDimension(R.dimen.fal_horizontal_margin).toInt() +
                76.fromDps(context)

        gutter = (context.resources.getDimension(R.dimen.fal_list_gutter).toInt() / 2).toInt()
    }

    override fun getItemOffsets(outRect: Rect, view: View, parent: RecyclerView, state: RecyclerView.State) {
        val position = parent.getChildAdapterPosition(view)
        val numberOfChilds = state.itemCount

        outRect.left = parent.resources.getDimension(R.dimen.fal_horizontal_margin).toInt()
        outRect.top = gutter
        outRect.right = parent.resources.getDimension(R.dimen.fal_horizontal_margin).toInt()
        outRect.bottom = gutter

        if(position == 0)
            outRect.top = 0
        else if(position == (numberOfChilds - 1)){
            outRect.bottom = 0
        }
    }

    override fun onDraw(canvas: Canvas, parent: RecyclerView, state: RecyclerView.State) {
        verticalDivider(canvas,parent)
    }

    private fun verticalDivider(c: Canvas, parent: RecyclerView) {
        val left = parent.paddingLeft + dividerMarginLeft
        val right = parent.width - dividerMarginRight

        val childCount = parent.childCount
        for (i in 0 until childCount) {
            val child = parent.getChildAt(i)
            val params = child
                .layoutParams as RecyclerView.LayoutParams
            val top = child.bottom + params.bottomMargin
            c.drawLine(left.toFloat(), top.toFloat(), right.toFloat(), top.toFloat(), paint)
        }
    }
}