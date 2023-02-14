package com.example.bookzone.ui.wishlist

import androidx.compose.foundation.background
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import com.example.bookzone.ui.home.BookCard

@Composable
fun WishlistPage(){
    LazyColumn(modifier = Modifier.background(color = Color.White)){
        items(3)
        {
            BookCard()
        }
    }
}

@Preview
@Composable
fun WishlistPagePreview(){
    WishlistPage()
}