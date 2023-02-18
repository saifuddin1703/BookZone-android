package com.example.bookzone.ui.book.repository

import com.example.bookzone.retrofit.responseBody.book.BooksResponse
import com.example.bookzone.retrofit.services.BookService
import javax.inject.Inject

class BookRepository @Inject constructor(
    private val bookService: BookService
) {

    suspend fun getBooks(options : Map<String,String>): BooksResponse {
        return bookService.getBooks(options,null,null)
    }

    suspend fun getBookById(id : String) = bookService.getBook(id)
}