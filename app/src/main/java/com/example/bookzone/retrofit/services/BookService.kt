package com.example.bookzone.retrofit.services

import com.example.bookzone.retrofit.responseBody.book.BookResponse
import com.example.bookzone.retrofit.responseBody.book.BooksResponse
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query
import retrofit2.http.QueryMap

interface BookService {

    @GET("/api/book")
    suspend fun getBooks(
        @QueryMap map: Map<String,String>,
        @Query("sort") sort : List<String>?,
        @Query("format") format : List<String>?
    ) : BooksResponse

    @GET("/api/book/{id}")
    suspend fun getBook(@Path("id") id : String) : BookResponse

}