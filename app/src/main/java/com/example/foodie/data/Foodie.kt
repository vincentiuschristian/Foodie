package com.example.foodie.data

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Foodie(
    var name: String,
    var time: String,
    var servings: String,
    var photo: Int,
    var ingredients: String,
    var directions: String,
    var calories: String,
): Parcelable