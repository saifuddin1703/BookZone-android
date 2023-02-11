package com.example.bookzone.model

data class Book(
    val _id: String = "",
    val ratingsQuantity: Int = 0,
    val image: String = "",
    val name: String = "",
    val author: String = "",
    val format: String = "",
    val book_depository_stars:Number = 5,
    val price: Number = 0,
    val category: String = "",
    val reviews: List<Review> = listOf()
)
