package com.example.bookzone.ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion.CenterVertically
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.bookzone.R
import com.example.bookzone.ui.theme.secondaryTextColor

@Composable
fun StarBar(rating : Float,ratingCount : Int?,starSize : Dp){

    val ratings = ((rating * 10) / 10).toInt()
    val decimal = (rating * 10) % 10

    Row(modifier = Modifier
        .background(color = Color.White)
    ) {
        for (i in 0 until ratings){
            Image(painter = painterResource(R.drawable.star),contentDescription = null, modifier = Modifier.size(starSize))
        }
        if (decimal != 0f)
            Image(painter = painterResource(R.drawable.half_start),contentDescription = null,modifier = Modifier.size(starSize))

        ratingCount?.let {
            Text(text = "(${it})",
                fontWeight = FontWeight.Medium,
                fontSize = 17.sp,
                color = secondaryTextColor,
                modifier = Modifier.align(alignment = CenterVertically).padding(start = 5.dp)
            )
        }

    }
}

@Preview
@Composable
fun StarBarPreview(){
    StarBar(2.7f,189,20.dp)
}