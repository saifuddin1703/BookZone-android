package com.example.bookzone.ui.book

import android.R.attr.maxLines
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.SubcomposeAsyncImage
import com.example.bookzone.R
import com.example.bookzone.model.Book
import com.example.bookzone.ui.components.StarBar
import com.example.bookzone.ui.theme.BookZoneTheme
import com.example.bookzone.ui.theme.primaryButtonColor
import com.example.bookzone.ui.theme.secondaryButtonColor
import com.example.bookzone.ui.theme.secondaryTextColor


@Composable
fun BookCard(book : Book){

    Row(modifier = Modifier
        .fillMaxWidth()
        .background(color = Color.White))
    {

        SubcomposeAsyncImage(
            model = book.image,
            loading = {
                CircularProgressIndicator(
                    modifier = Modifier
                        .size(50.dp)
                        .align(alignment = Alignment.Center),
                    color = primaryButtonColor
                )
            },
            contentDescription = "Book image",
            modifier = Modifier
                .padding(start = 10.dp, end = 10.dp, top = 10.dp, bottom = 10.dp)
                .width(161.dp)
                .height(244.dp)
        )

        Column(
            modifier = Modifier.padding(top = 10.dp,bottom = 10.dp, end = 10.dp)
        ){

            Text(
                text = book.name,
                fontWeight = FontWeight.SemiBold,
                fontSize = 20.sp,
                maxLines = 2,
                overflow = TextOverflow.Ellipsis
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
                modifier = Modifier.padding(top = 30.dp, bottom = 10.dp)
            )

            Row {
                BuyButton()

                AddToCartButton()
            }
        }
    }
}

@Composable
fun AddToCartButton(){
    Box(
        modifier = Modifier
            .background(color = secondaryButtonColor, shape = RoundedCornerShape(7.dp))
    ) {
        Icon(
            painter = painterResource(id = R.drawable.cart_icon),
            contentDescription = "buy icon",
            modifier = Modifier
                .padding(10.dp)
                .size(25.dp)
                .align(alignment = Alignment.Center),
            tint = Color.White
        )
    }
}
@Composable
fun BuyButton(){
    Row(
        modifier = Modifier
            .padding(end = 10.dp)
            .background(color = primaryButtonColor, shape = RoundedCornerShape(7.dp))
    ) {
        Icon(
            painter = painterResource(id = R.drawable.buy_icon),
            contentDescription = "buy icon",
            modifier = Modifier
                .padding(5.dp)
                .width(30.dp)
                .height(35.dp)
                .align(alignment = Alignment.CenterVertically),
            tint = Color.White
        )
        Spacer(modifier = Modifier.width(15.dp))
        Text(
            text = "Buy",
            modifier = Modifier
                .padding(5.dp)
                .align(alignment = Alignment.CenterVertically),
            fontWeight = FontWeight.SemiBold,
            fontSize = 24.sp,
            color = Color.White
        )

        Spacer(modifier = Modifier.width(30.dp))
    }
}
@Preview
@Composable
fun BookCardPreview(){
    BookZoneTheme {
        BookCard(Book(
        image = "https://d1w7fb2mkkr3kw.cloudfront.net/assets/images/book/mid/9780/7295/9780729541947.jpg",
        name = "Jarvis'S Physical Examination and Health Assessment Anz 2e",
        author = "Forbes",
        format = "Paperback",
        book_depository_stars = 0,
        ratingsQuantity = 0,
        price = 7614,
        category = "Medical",
        ))
    }
}