package com.example.bookzone.ui.categories

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview

@Composable
fun CategoryPage(){

    Column(modifier = Modifier
        .fillMaxSize()
        .background(color = Color.White)) {

    }
}

@Preview
@Composable
fun CategoryPagePreview(){
    CategoryPage()
}