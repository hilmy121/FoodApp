package com.onappfeedback.foodappdemo

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class PaymentFragment: Fragment() {

    var rvAmountDetails : RecyclerView? = null

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val rootView = inflater.inflate(R.layout.fragment_payment,container,false)

        rvAmountDetails = rootView.findViewById<RecyclerView>(R.id.rv_amount_details)

        return rootView
    }

    @SuppressLint("WrongConstant")
    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        // Amount Details
        rvAmountDetails?.layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL,false)
        rvAmountDetails?.addItemDecoration(AmountItemDecoration(requireContext()))

        val list: MutableList<AmountItem> = mutableListOf(
            AmountItem("Food Charges","₹ 150"),
            AmountItem("Delivery Charges","₹ 30"),
            AmountItem("Offers","₹ -30",colored = true)
        )

        val listAdapter = AmountAdapter(list)
        rvAmountDetails?.adapter = listAdapter



        listAdapter.notifyDataSetChanged()
    }

}