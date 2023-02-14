package com.example.bookzone.ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.bookzone.R
import com.example.bookzone.ui.theme.primaryButtonColor

@Composable
fun PrimaryButton(
    modifier: Modifier,
    isLoading : Boolean,
    onClick : ()->Unit
){
    Box(
        modifier = modifier
            .clickable(enabled = !isLoading,onClick = onClick)
    ) {
        if (!isLoading)
            Image(
                painter = painterResource(id = R.drawable.buy_icon),
                contentDescription = "buy icon",
                modifier = Modifier
                    .padding(5.dp)
                    .width(48.dp)
                    .height(50.dp)
                    .align(alignment = Alignment.CenterStart),
                contentScale = ContentScale.Crop
            )
        else
            CircularProgressIndicator(
                modifier = Modifier
                    .padding(5.dp)
                    .width(50.dp)
                    .height(50.dp)
                    .align(alignment = Alignment.CenterStart),
                color = Color.White,
                strokeWidth = 5.dp
            )

        Text(
            text = "Proceed to buy (3 items)",
            modifier = Modifier
                .padding(5.dp)
                .align(alignment = Alignment.Center),
            fontWeight = FontWeight.SemiBold,
            fontSize = 17.sp,
            color = Color.White
        )

        Spacer(modifier = Modifier.width(30.dp))
    }
}