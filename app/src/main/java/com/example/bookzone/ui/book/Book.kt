package com.example.bookzone.ui.book

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.semantics.Role.Companion.Image
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.bookzone.R

@Composable
fun Book(){
    val book = com.example.bookzone.model.Book(
        _id = "id",
        image = "",
        name = "This is Going to Hurt",
        author = "Adam kay",
        format = "Paperback",
        book_depository_stars = 5,
        price = 649,
        category = "Medical"
    )

    Column(modifier = Modifier
        .fillMaxSize()
        .background(color = Color.White))
    {

        Image(
            painter = painterResource(id = R.drawable.book2),
            contentDescription = null,
            modifier = Modifier
                .padding(top = 20.dp, start = 10.dp,bottom = 10.dp,end = 10.dp)
                .height(250.dp)
                .width(180.dp),
            contentScale = ContentScale.Crop
        )

    }


}

@Preview
@Composable
fun BookPreview(){
    Book()
}