package com.example.bookzone.model

import com.google.gson.annotations.SerializedName

data class Book(
    @SerializedName("_id") val _id: String = "",
    @SerializedName("ratingsQuantity") val ratingsQuantity: Int = 0,
    @SerializedName("image") val image: String = "",
    @SerializedName("name") val name: String = "",
    @SerializedName("author") val author: String = "",
    @SerializedName("format") val format: String = "",
    @SerializedName("book_depository_stars") val book_depository_stars:Number = 5,
    @SerializedName("price") val price: Number = 0,
    @SerializedName("category") val category: String = "",
    @SerializedName("reviews") val reviews: List<Review> = listOf()
)
