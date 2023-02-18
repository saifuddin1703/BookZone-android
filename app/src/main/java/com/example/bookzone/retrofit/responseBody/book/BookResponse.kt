package com.example.bookzone.retrofit.responseBody.book

import com.example.bookzone.model.Book
import com.google.gson.annotations.SerializedName

data class BookResponse(
    @SerializedName("status") val status : String = "",
    @SerializedName("data") val data : Book = Book()
)
