package com.example.bookzone.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.ModalBottomSheetState
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.example.bookzone.R
import com.example.bookzone.ui.home.BookCard
import com.example.bookzone.ui.theme.MediumText
import com.example.bookzone.ui.theme.filterColor
import com.example.bookzone.ui.theme.smallText
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun BookList(title : String,sheetState: ModalBottomSheetState){
    Column(modifier = Modifier
        .padding(bottom = 90.dp)
        .fillMaxHeight()
        .width(480.dp)
    ) {

        Box(modifier = Modifier
            .height(48.dp)
            .fillMaxWidth()
            .background(color = Color.White)
        ){

            val scope = rememberCoroutineScope()

            Text(
                text = title,
                modifier = Modifier
                    .padding(start = 10.dp)
                    .align(alignment = Alignment.CenterStart),
                fontWeight = MediumText.fontWeight,
                fontSize = MediumText.fontSize,
                color = Color.Black
            )

            Row(
                modifier = Modifier
                    .padding(end = 10.dp)
                    .align(alignment = Alignment.CenterEnd)
                    .background(color = filterColor, shape = RoundedCornerShape(7.dp))
                    .clickable {
                        scope.launch {
                            sheetState.show()
                        }
                    }
            ) {
                Icon(painter = painterResource(id = R.drawable.filter),
                    contentDescription = null,
                    modifier = Modifier
                        .padding(5.dp)
                        .size(15.dp)
                        .align(alignment = Alignment.CenterVertically)
                )

                Text(
                    text = "Filters",
                    fontSize = smallText.fontSize,
                    fontWeight = smallText.fontWeight,
                    modifier = Modifier
                        .padding(start = 5.dp, end = 5.dp)
                        .align(alignment = Alignment.CenterVertically)
                )
            }
        }

        LazyColumn(modifier = Modifier.background(color = Color.White)){
            items(3)
            {
                BookCard()
            }
        }
    }
}