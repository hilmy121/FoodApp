package com.onappfeedback.foodappdemo

import androidx.appcompat.widget.DialogTitle

data class FoodDataItem (
    var imageResourceId: Int = 0,
    var title: String = "My Default Title",
    var category: String = "My Default Category",
    var price: String = "$00.00",
    var rating: String = "1.0 Rating",
    var bookmarked: Boolean = false
)