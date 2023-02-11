package com.example.bookzone.ui.categories

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.bookzone.R
import com.example.bookzone.utlis.data.categories

@Composable
fun CategoryPage(){

    LazyColumn(modifier = Modifier
        .fillMaxSize()
        .background(color = Color.White)) {

        items(categories){category ->
            CategoryCard(name = category.name, image = category.thumbnail)
        }
    }
}

@Composable
fun CategoryCard(name : String,image : Int){
    Box(modifier = Modifier.padding(15.dp)) {
        Image(
            painter = painterResource(id = image),
            modifier = Modifier
                .height(100.dp)
                .fillMaxWidth()
                .clip(shape = RoundedCornerShape(15.dp)),
            contentDescription = null,
            contentScale = ContentScale.Crop
        )

        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(100.dp)
                .background(
                    brush = Brush.linearGradient(colors = listOf(Color.Black, Color.Black)),
                    alpha = 0.5f,
                    shape = RoundedCornerShape(15.dp)
                )
        ) {
            Text(
                text = name,
                color = Color.White,
                fontSize = 27.sp,
                modifier = Modifier
                    .padding(10.dp)
                    .align(alignment = Alignment.BottomStart),
                fontWeight = FontWeight.SemiBold
            )
        }
    }
}

@Preview
@Composable
fun CardPreview(){
    CategoryCard(name = "Medical", image = R.drawable.medical)
}
@Preview
@Composable
fun CategoryPagePreview(){
    CategoryPage()
}