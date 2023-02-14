package com.example.bookzone.ui.cart

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion.Center
import androidx.compose.ui.Alignment.Companion.CenterEnd
import androidx.compose.ui.Alignment.Companion.CenterHorizontally
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.bookzone.R
import com.example.bookzone.model.Book
import com.example.bookzone.ui.components.PrimaryButton
import com.example.bookzone.ui.components.StarBar
import com.example.bookzone.ui.theme.*
import kotlinx.coroutines.delay

@Composable
fun CartPage(){

    var isLoading by remember {
        mutableStateOf(false)
    }

    Column(
        modifier = Modifier.background(color = Color.White)
    ) {
            PrimaryButton(
                modifier = Modifier
                    .padding(10.dp)
                    .fillMaxWidth()
                    .background(color = primaryButtonColor, shape = RoundedCornerShape(7.dp)),
                isLoading = isLoading
            ){
                isLoading = true
            }


        LazyColumn(){
            items(3){
                Cart()
            }
        }
    }

    LaunchedEffect(key1 = isLoading){
        delay(1500)
        isLoading = false
    }
}

@Composable
fun Cart(){
    val book = Book(
        _id = "id",
        image= "",
        name = "This is Going to Hurt",
        author = "Adam kay",
        format = "Paperback",
        book_depository_stars = 5,
        price = 649,
        category = "medical"
    )

    Row(modifier = Modifier
        .fillMaxWidth()
        .background(color = Color.White))
    {

        var quantity by remember {
            mutableStateOf(1)
        }

        // quantity control buttons
        Column {
            Image(
                painter = painterResource(id = R.drawable.book2),
                contentDescription = "Book image",
                modifier = Modifier
                    .padding(start = 10.dp, end = 10.dp, top = 10.dp, bottom = 10.dp)
                    .width(151.dp)
                    .height(200.dp)
                    .align(alignment = CenterHorizontally),
                contentScale = ContentScale.Crop
            )

            Box(modifier = Modifier
                .padding(start = 10.dp, end = 10.dp, bottom = 10.dp)
                .width(151.dp)
                .height(35.dp)
                .border(0.5.dp, Color.Black, shape = RoundedCornerShape(50))
            ){
                Box(modifier = Modifier
                    .size(35.dp)
                    .border(1.5.dp, secondaryColor, shape = CircleShape)
                    .background(color = secondaryColor, shape = CircleShape)
                    .clip(shape = CircleShape)
                    .clickable {
                        if (quantity > 0)
                            quantity--
                    }
                ){
                    Text(
                        text = "-",
                        fontWeight = FontWeight.SemiBold,
                        fontSize = 25.sp,
                        modifier = Modifier.align(alignment = Center),
                        textAlign = TextAlign.Center
                    )
                }

                Text(
                    text = "$quantity",
                    fontWeight = FontWeight.SemiBold,
                    fontSize = 25.sp,
                    modifier = Modifier.align(alignment = Center),
                    textAlign = TextAlign.Center
                )

                Box(modifier = Modifier
                    .size(35.dp)
                    .border(1.5.dp, primaryButtonColor, shape = CircleShape)
                    .background(color = primaryButtonColor, shape = CircleShape)
                    .align(alignment = CenterEnd)
                    .clip(shape = CircleShape)
                    .clickable {
                        quantity++
                    }
                ){
                    Text(
                        text = "+",
                        fontWeight = FontWeight.SemiBold,
                        fontSize = 25.sp,
                        modifier = Modifier
                            .wrapContentHeight()
                            .align(alignment = Center),
                        textAlign = TextAlign.Center,
                        color = Color.White
                    )
                }
            }
        }

        Column(
            modifier = Modifier.padding(top = 10.dp,bottom = 10.dp, end = 10.dp)
        ){

            Text(
                text = book.name,
                fontWeight = FontWeight.SemiBold,
                fontSize = 24.sp
            )

            Text(
                text = "by ${book.author}",
                fontWeight = FontWeight.Medium,
                fontSize = 14.sp,
                color = secondaryTextColor,
                modifier = Modifier.padding(bottom = 5.dp)
            )

            StarBar(
                rating = book.book_depository_stars.toFloat(),
                ratingCount = 189,
                starSize = 17.dp
            )

            Text(
                text = book.format,
                fontWeight = FontWeight.Medium,
                fontSize = 14.sp,
            )

            Text(
                text = "₹ ${book.price}",
                fontWeight = FontWeight.SemiBold,
                fontSize = 27.sp,
                modifier = Modifier.padding(top = 40.dp, bottom = 10.dp)
            )

            Row {
                Row(
                    modifier = Modifier
                        .padding(end = 10.dp)
                        .background(color = Color.White, shape = RoundedCornerShape(7.dp))
                        .border(1.dp, color = primaryButtonColor, shape = RoundedCornerShape(7.dp))
                ) {
                    Text(
                        text = "Delete",
                        modifier = Modifier
                            .padding(start = 10.dp, end = 10.dp, top = 5.dp, bottom = 5.dp)
                            .align(alignment = Alignment.CenterVertically),
                        fontWeight = FontWeight.SemiBold,
                        fontSize = 17.sp,
                        color = primaryButtonColor
                    )
                }

                Row(
                    modifier = Modifier
                        .padding(end = 10.dp)
                        .background(color = primaryButtonColor, shape = RoundedCornerShape(7.dp))
                ) {
                    Text(
                        text = "Add to wishlist",
                        modifier = Modifier
                            .padding(start = 5.dp, end = 5.dp, top = 5.dp, bottom = 5.dp)
                            .align(alignment = Alignment.CenterVertically),
                        fontWeight = FontWeight.SemiBold,
                        fontSize = 17.sp,
                        color = Color.White
                    )
                }
            }
        }
    }
}

@Preview
@Composable
fun CartPreview(){
    Cart()
}
@Preview
@Composable
fun CartPagePreview(){
    CartPage()
}