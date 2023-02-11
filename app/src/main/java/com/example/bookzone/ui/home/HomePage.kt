package com.example.bookzone.ui.home

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment.Companion.BottomCenter
import androidx.compose.ui.Alignment.Companion.Center
import androidx.compose.ui.Alignment.Companion.CenterEnd
import androidx.compose.ui.Alignment.Companion.CenterStart
import androidx.compose.ui.Alignment.Companion.CenterVertically
import androidx.compose.ui.Alignment.Companion.Start
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.bookzone.R
import com.example.bookzone.model.Book
import com.example.bookzone.ui.components.BookList
import com.example.bookzone.ui.components.StarBar
import com.example.bookzone.ui.theme.*
import kotlinx.coroutines.launch


@OptIn(ExperimentalMaterialApi::class)
@Composable
fun HomePage(){

    val sheetState = rememberModalBottomSheetState(
        initialValue = ModalBottomSheetValue.Expanded,
        skipHalfExpanded = true
    )

   BookList(title = "Top rated", sheetState = sheetState)

    FiltersBottomSheet(
        sheetState
    )
}

@Composable
fun FormatCard(name : String){

    Row(modifier = Modifier
        .padding(end = 10.dp)
        .background(color = primaryButtonColor, shape = RoundedCornerShape(50))
    ){
        Text(
            text = name,
            fontSize = MediumText.fontSize,
            fontWeight = MediumText.fontWeight,
            color = Color.White,
            modifier = Modifier.padding(start = 20.dp, end = 10.dp, top = 5.dp, bottom = 5.dp)
        )
        
        Text(
            text = "X",
            textAlign = TextAlign.Center,
            modifier = Modifier
                .padding(end = 5.dp, top = 5.dp, bottom = 5.dp)
                .size(20.dp)
                .background(color = Color.White, shape = CircleShape),
            color = primaryButtonColor,
//            textAlign = Center,
            fontWeight = FontWeight.ExtraBold
        )
        
    }
}

@OptIn(ExperimentalMaterialApi::class, ExperimentalMaterial3Api::class)
@Composable
fun FiltersBottomSheet(sheetState: ModalBottomSheetState) {
    ModalBottomSheetLayout (
        sheetState = sheetState,
        sheetContent =
        {

            val formats = listOf(
                "Paperback",
                "Hardback"
            )

            Column(modifier = Modifier
                .fillMaxSize()
                .background(color = Color.White))
            {

                Box(modifier = Modifier
                    .padding(start = 20.dp, end = 20.dp, top = 10.dp)
                    .fillMaxWidth()){
                    Text(
                        text = "Filters",
                        fontSize = MediumText.fontSize,
                        fontWeight = MediumText.fontWeight,
                        modifier = Modifier.align(alignment = Center)
                    )

                    Text(
                        text = "Reset",
                        fontSize = MediumText.fontSize,
                        fontWeight = MediumText.fontWeight,
                        modifier = Modifier.align(alignment = CenterEnd),
                        textDecoration = TextDecoration.Underline
                    )
                }

                Box(modifier = Modifier
                    .padding(start = 20.dp, end = 20.dp, top = 10.dp)
                    .fillMaxWidth()){
                    Text(
                        text = "Formats",
                        fontSize = MediumText.fontSize,
                        fontWeight = MediumText.fontWeight,
                        modifier = Modifier.align(alignment = CenterStart)
                    )

                    Text(
                        text = "View All >",
                        fontSize = MediumText.fontSize,
                        fontWeight = MediumText.fontWeight,
                        modifier = Modifier.align(alignment = CenterEnd),
                    )
                }

                LazyRow(
                    modifier = Modifier
                        .padding(start = 20.dp, end = 20.dp, top = 10.dp)
                ){
                    items(formats){format ->
                        FormatCard(name = format)
                    }
                }


                Text(
                    text = "Price",
                    fontSize = MediumText.fontSize,
                    fontWeight = MediumText.fontWeight,
                    modifier = Modifier
                        .padding(start = 20.dp, top = 10.dp)
                        .align(alignment = Start)
                )

                Box(modifier = Modifier.padding(start = 20.dp, end = 20.dp)) {
                    var priceRange by remember {
                        mutableStateOf(0f..50000f) // pass the initial values
                    }
                    androidx.compose.material3.RangeSlider(
                        values = priceRange,
                        onValueChange = { sliderValues_ ->
                            priceRange = sliderValues_
                        },
                        valueRange = 0f..50000f,
                        onValueChangeFinished = {
                            // this is called when the user completed selecting the value

                        },
                        colors = androidx.compose.material3.SliderDefaults.colors(
                            thumbColor = Color.Black,
                            activeTrackColor = Color.Black
                        ),
                        steps = 100
                    )

                    Text(
                        text = "₹ ${priceRange.start.toInt()}",
                        fontSize = MediumText.fontSize,
                        fontWeight = MediumText.fontWeight,
                        modifier = Modifier
                            .padding(top = 40.dp)
                            .align(alignment = CenterStart),
                        color = secondaryTextColor
                    )

                    Text(
                        text = "₹ ${priceRange.endInclusive.toInt()}",
                        fontSize = MediumText.fontSize,
                        fontWeight = MediumText.fontWeight,
                        modifier = Modifier
                            .padding(top = 40.dp)
                            .align(alignment = CenterEnd),
                        color = secondaryTextColor
                    )
                }
                
                FilterBox(title = "Customer Ratings") {
                    Column(
                        modifier = Modifier.padding(top = 10.dp)
                    ){

                        var selectedRating by remember {
                            mutableStateOf(4)
                        }

                        for (stars in 4 downTo 1)
                            CustomerRatingFilter(
                                selected = selectedRating == stars,
                                onSelect = {
                                    selectedRating = stars
                                },
                                stars = stars
                            )

                    }
                }

                FilterBox(title = "Sort by ") {

                    Column {
                        SortByFilter(title = "Price")
                        {

                        }

                        SortByFilter(title = "Ratings")
                        {

                        }
                    }
                }

                Box(modifier = Modifier.fillMaxSize()) {
                    Button(
                        onClick = {

                        },
                        colors = ButtonDefaults.buttonColors(
                            backgroundColor = primaryButtonColor,
                            contentColor = Color.White
                        ),
                        modifier = Modifier
                            .padding(start = 20.dp, end = 20.dp, top = 10.dp, bottom = 20.dp)
                            .fillMaxWidth()
                            .align(alignment = BottomCenter),
                        shape = RoundedCornerShape(10.dp)
                    ) {
                        Text(
                            text = "Apply",
                            fontSize = ExtraLargeText.fontSize,
                            fontWeight = FontWeight.SemiBold,
                            color = Color.White
                        )
                    }
                }

            }
        },
        sheetBackgroundColor = Color.White,
        sheetShape = RoundedCornerShape(topStart = 20.dp, topEnd = 20.dp),
        sheetContentColor = Color.White,
        modifier = Modifier.padding(top = 10.dp)
    ){

    }
}


@Composable
fun CustomerRatingFilter(
    selected : Boolean,
    onSelect : () -> Unit,
    starSize : Dp = 25.dp,
    stars : Int = 1
){

    Box(modifier = Modifier.fillMaxWidth()){
        Row {
            StarBar(rating = stars.toFloat(), ratingCount = null, starSize = starSize)
            Text(
                text = "& up",
                fontSize = MediumText.fontSize,
                fontWeight = MediumText.fontWeight,
                modifier = Modifier
                    .padding(start = starSize.times(4 - stars) + 30.dp)
            )
        }

        RadioButton(
            selected = selected,
            onClick = onSelect,
            modifier = Modifier
                .align(alignment = CenterEnd)
                .size(25.dp)
                .padding(end = 20.dp),
            colors = RadioButtonDefaults.colors(
                selectedColor = Color.Black
            )
        )
    }
}

@Composable
fun SortByFilter(
    title: String,
    onSelect: (order : Int) -> Unit
){
    var selectedOrder by remember {
        mutableStateOf(-1)
    }
    Box(modifier = Modifier
        .padding(top = 5.dp)
        .fillMaxWidth()){
       Text(
            text = "$title : High to Low",
            fontSize = MediumText.fontSize,
            fontWeight = MediumText.fontWeight,
           color = secondaryTextColor
       )

        RadioButton(
            selected = selectedOrder == -1,
            onClick = {
                onSelect(selectedOrder)
                selectedOrder = -1
            },
            modifier = Modifier
                .align(alignment = CenterEnd)
                .size(25.dp)
                .padding(end = 20.dp),
            colors = RadioButtonDefaults.colors(
                selectedColor = Color.Black
            )
        )
    }

    Box(modifier = Modifier
        .padding(top = 5.dp)
        .fillMaxWidth()){
        Text(
            text = "$title : Low to High",
            fontSize = MediumText.fontSize,
            fontWeight = MediumText.fontWeight,
            color = secondaryTextColor
        )

        RadioButton(
            selected = selectedOrder == 1,
            onClick =
            {
                onSelect(selectedOrder)
                selectedOrder = 1
            },
            modifier = Modifier
                .align(alignment = CenterEnd)
                .size(25.dp)
                .padding(end = 20.dp),
            colors = RadioButtonDefaults.colors(
                selectedColor = Color.Black
            )
        )
    }
}

@Composable
fun FilterBox(title : String,content : @Composable () -> Unit){

    Column(modifier = Modifier.padding(start = 20.dp)) {
        Text(
            text = title,
            fontSize = MediumText.fontSize,
            fontWeight = MediumText.fontWeight,
            modifier = Modifier
                .padding(top = 10.dp)
                .align(alignment = Start)
        )

        content()
    }
}

@Preview
@Composable
fun FormatPreview(){
    FormatCard(name = "Paperback")
}
@Composable
fun BookCard(){

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
        .background(color = Color.White)) {

        Image(
            painter = painterResource(id = R.drawable.book2),
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
                modifier = Modifier.padding(top = 30.dp, bottom = 10.dp)
            )

            Row {
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
                            .align(alignment = CenterVertically),
                        tint = Color.White
                    )
                    Spacer(modifier = Modifier.width(15.dp))
                    Text(
                        text = "Buy",
                        modifier = Modifier
                            .padding(5.dp)
                            .align(alignment = CenterVertically),
                        fontWeight = FontWeight.SemiBold,
                        fontSize = 24.sp,
                        color = Color.White
                    )
                    
                    Spacer(modifier = Modifier.width(30.dp))
                }

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
                            .align(alignment = Center),
                        tint = Color.White
                    )
                }
            }
        }
    }

}

@Preview
@Composable
fun BookCardPreview(){
    BookZoneTheme {
        BookCard()
    }
}
@Preview
@Composable
fun HomePagePreview(){
    HomePage()
}