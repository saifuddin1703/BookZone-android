package com.example.bookzone.ui.home

import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.snapshots.SnapshotStateMap
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
import androidx.core.util.rangeTo
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import coil.compose.AsyncImage
import coil.compose.SubcomposeAsyncImage
import com.example.bookzone.R
import com.example.bookzone.model.Book
import com.example.bookzone.ui.book.viewmodel.BookViewModel
import com.example.bookzone.ui.book.BookList
import com.example.bookzone.ui.book.UIState
import com.example.bookzone.ui.components.StarBar
import com.example.bookzone.ui.theme.*


@OptIn(ExperimentalMaterialApi::class)
@Composable
fun HomePage(
    sheetState: ModalBottomSheetState,
//    bookViewModel: BookViewModel,
    filterOptions : Map<String,String>
){
    val bookViewModel = hiltViewModel<BookViewModel>()

    LaunchedEffect(key1 = filterOptions){
        Log.d("TAG","launced effect")
        bookViewModel.getBooks(filterOptions)
    }
    Log.d("TAG","home page")


    val uiState = bookViewModel.state.observeAsState()

//    when (uiState.value){
//        UIState.LOADING->{}
//        UIState.SUCCESS ->{}
//    }


    if (uiState.value?.isLoading == true){
        // loading page
        Box(modifier = Modifier.fillMaxSize()){
            CircularProgressIndicator(modifier = Modifier.align(alignment = Center))
        }
    }else{
        uiState.value?.message?.let {
            // error page
            Box(modifier = Modifier.fillMaxSize()){
                Text(text = "error loading books",modifier = Modifier.align(alignment = Center))
            }
        }

        uiState.value?.data?.let {
            BookList(title = "Top rated", sheetState = sheetState, books = it)
        }
    }

    
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
fun FiltersBottomSheet(
    sheetState: ModalBottomSheetState,
    onApply : (SnapshotStateMap<String, String>)->Unit
) {

    val filterOptions = remember {
        mutableStateMapOf<String,String>()
    }

    filterOptions["price[gte]"] = "0"
    filterOptions["price[lte]"] = "50000"
    filterOptions["book_depository_stars[gte]"] = "4"

    val defaultFilters = mutableMapOf<String,String>()
    defaultFilters["price[gte]"] = "0"
    defaultFilters["price[lte]"] = "50000"
    defaultFilters["book_depository_stars[gte]"] = "4"
//    defaultFilters["sort"] = "-price,-book_depository_stars"

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
                        modifier = Modifier
                            .clickable {
                                defaultFilters.forEach {
                                    filterOptions[it.key] = it.value
                                }
//                                Log.d
                            }
                            .align(alignment = CenterEnd),
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
                    var priceRange  = (filterOptions["price[gte]"]?.toFloat() ?: 0f)..(filterOptions["price[lte]"]?.toFloat()
                        ?: 50000f)
                    androidx.compose.material3.RangeSlider(
                        values = priceRange,
                        onValueChange = { sliderValues_ ->
                            priceRange = sliderValues_
                            filterOptions["price[gte]"] = "${priceRange.start}"
                            filterOptions["price[lte]"] = "${priceRange.endInclusive}"
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
                            mutableStateOf(filterOptions["book_depository_stars[gte]"]?.toInt())
                        }

                        filterOptions["book_depository_stars[gte]"] = "$selectedRating"

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
//                            filterOptions["sort"]?.set(0, if (it == -1) "-price" else "price")
                        }

                        SortByFilter(title = "Ratings")
                        {
//                            filterOptions["sort"]?.set(0, if (it == -1) "-book_depository_stars" else "book_depository_stars")
                        }
                    }
                }

                Box(modifier = Modifier.fillMaxSize()) {
                    Button(
                        onClick = {
                                  onApply(filterOptions)
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
        modifier = Modifier.padding(top = 66.dp)
    ){

    }
}


@OptIn(ExperimentalMaterialApi::class)
@Preview
@Composable
fun BottomSheetPreview(){
    FiltersBottomSheet(sheetState = ModalBottomSheetState(initialValue = ModalBottomSheetValue.Expanded)){

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

@OptIn(ExperimentalMaterialApi::class)
@Preview
@Composable
fun HomePagePreview(){
    HomePage(rememberModalBottomSheetState(initialValue = ModalBottomSheetValue.Hidden), filterOptions = mapOf())
}