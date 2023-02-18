package com.example.bookzone.retrofit.responseBody.book

import com.example.bookzone.model.Book
import com.google.gson.annotations.SerializedName

data class BooksResponse(
    @SerializedName("status") val status : String = "",
    @SerializedName("data") val data : List<Book> = listOf()
)
