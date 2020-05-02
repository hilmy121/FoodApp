package com.onappfeedback.foodappdemo

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView

class AmountAdapter(val items: MutableList<AmountItem>): RecyclerView.Adapter<AmountAdapter.AmountHolder>(){

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AmountHolder {
        val listItem = LayoutInflater.from(parent.context).inflate(R.layout.item_account_details,parent,false)
        return AmountHolder(listItem)
    }

    override fun getItemCount(): Int {
        return items.size
    }

    override fun onBindViewHolder(holder: AmountHolder, position: Int) {
        holder.bind(items.get(position))
    }

    class AmountHolder(val amountView: View): RecyclerView.ViewHolder(amountView){
        var description: TextView = amountView.findViewById(R.id.text_description)
        var value: TextView = amountView.findViewById(R.id.text_value)

        fun bind(amount: AmountItem){
            description.text = amount.description
            value.text = amount.value

            if(amount.colored){
                value.setTextColor(ContextCompat.getColor(amountView.context,R.color.falColorSecondary))
            }
        }
    }
}