package com.example.bookzone

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.ModalBottomSheetValue
import androidx.compose.material.rememberModalBottomSheetState
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.*
import androidx.compose.runtime.snapshots.SnapshotStateMap
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.bookzone.ui.book.repository.BookRepository
import com.example.bookzone.ui.book.viewmodel.BookViewModel
import com.example.bookzone.ui.cart.CartPage
import com.example.bookzone.ui.categories.BooksOfCategory
import com.example.bookzone.ui.categories.CategoryPage
import com.example.bookzone.ui.components.CustomTopAppBar
import com.example.bookzone.ui.home.FiltersBottomSheet
import com.example.bookzone.ui.home.HomePage
import com.example.bookzone.ui.theme.BookZoneTheme
import com.example.bookzone.ui.wishlist.WishlistPage
import com.example.bookzone.utlis.models.NavItem
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch
import javax.inject.Inject


@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    @Inject lateinit var bookRepository: BookRepository
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        try {
            Log.d("TAG",bookRepository.toString())
        }catch (e : Exception){
            Log.d("TAG",e.message.toString())
        }
        setContent {
            BookZoneTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    val navController = rememberNavController()
                    Log.d("TAg","start")
                    LandingPage(navController)
                }
            }
        }
    }
}

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class, ExperimentalMaterialApi::class)
@Composable
fun LandingPage(navController: NavHostController) {


    val sheetState = rememberModalBottomSheetState(
        initialValue = ModalBottomSheetValue.Hidden,
        skipHalfExpanded = true
    )

    var filterOptions = remember {
        mutableStateMapOf<String,String>()
    }

    filterOptions["price[gte]"] = "0"
    filterOptions["price[lte]"] = "50000"
    filterOptions["book_depository_stars[gte]"] = "4"

//    filterOptions["sort"] = "-price,-book_depository_stars"

    Scaffold(modifier = Modifier
        .background(color = Color.White)
        .fillMaxSize()
    , topBar = {
        CustomTopAppBar()
        },
        bottomBar = {
            val items = listOf(
                NavItem.Home,
                NavItem.Categories,
                NavItem.Wishlist,
                NavItem.Cart
            )

            var selectedItem by remember {
                mutableStateOf(0)
            }

            BottomAppBar(
                containerColor = Color.White,
                modifier =  Modifier.height(90.dp)
            ) {
                items.forEachIndexed {index,item->
                    NavigationBarItem(selected = selectedItem == index,
                        onClick = { 
                            selectedItem = index

                            when(index){
                                0 ->{
                                    navController.navigate("home")
                                }
                                1 ->{
                                    navController.navigate("categories")
                                }
                                2 ->{
                                    navController.navigate("wishlist")
                                }
                                3 ->{
                                    navController.navigate("cart")
                                }

                            }
                        },
                        label = {
                            Text(
                                text = item.title,
                                fontSize = 17.sp,
                                fontWeight = FontWeight.SemiBold,
                                modifier = Modifier.padding(top = 5.dp)
                            )
                        },
                        icon = {
                            Image(painter = painterResource(id = item.iconId),
                                modifier = Modifier.size(30.dp),
                                contentDescription = "icon")
                        }
                    )
                }
            }
        }
    ){


        NavHost(navController = navController, startDestination = "home",modifier = Modifier.padding(top = 56.dp, bottom = 90.dp)){
            composable("home"){
                val viewmodel : BookViewModel = hiltViewModel()
                Log.d("TAG", viewmodel.toString())
                Log.d("TAG", hiltViewModel<BookViewModel>().toString())
                HomePage(sheetState,filterOptions = filterOptions.toMap())
            }
            composable("categories"){
                CategoryPage(navController)
            }
            composable("wishlist"){
                WishlistPage()
            }
            composable("cart"){
                CartPage()
            }
            composable("booksOfCategory/{name}", arguments = listOf(
                navArgument("name"){
                    type = NavType.StringType
                }
            )){
                it.arguments?.getString("name")?.let {name->
                    BooksOfCategory(category = name,sheetState)
                }
            }
        }
    }

    val scope = rememberCoroutineScope()
    FiltersBottomSheet(
        sheetState
    ){
        try {
            scope.launch {
                sheetState.hide()
            }
            it.forEach { action->
                filterOptions[action.key] = action.value
            }
            Log.d("TAG",filterOptions.toMap().toString())
        }catch (e : java.lang.Exception){
            Log.d("ERROR",e.message.toString())
        }
    }

}

@Composable
fun Greeting(name: String) {
    Text(text = "Hello $name!")
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    BookZoneTheme {
        LandingPage(rememberNavController())
    }
}