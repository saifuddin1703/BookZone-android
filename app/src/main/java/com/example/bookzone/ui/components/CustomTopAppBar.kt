package com.example.bookzone.ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.semantics.Role.Companion.Image
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.bookzone.R

@Composable
fun CustomTopAppBar(){

    Box(modifier = Modifier
        .fillMaxWidth()
        .height(56.dp)
        .background(color = Color.White)) {

        Text(
            text = "BookZone",
            modifier = Modifier.align(alignment = Alignment.Center),
            color = Color.Black,
            fontWeight = FontWeight(700),
            fontSize = 21.sp
        )

        Image(
            painter = painterResource(id = R.drawable.default_profile),
            modifier = Modifier
                .padding(end = 10.dp)
                .size(44.dp)
                .align(alignment = Alignment.CenterEnd),
            contentDescription = "default profile icon")

    }
}

@Preview
@Composable
fun CustomTopAppBarPreview(){
    CustomTopAppBar()
}