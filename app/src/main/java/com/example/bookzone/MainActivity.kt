package com.example.bookzone

import android.annotation.SuppressLint
import android.os.Bundle
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
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.bookzone.ui.cart.CartPage
import com.example.bookzone.ui.categories.BooksOfCategory
import com.example.bookzone.ui.categories.CategoryPage
import com.example.bookzone.ui.components.CustomTopAppBar
import com.example.bookzone.ui.home.FiltersBottomSheet
import com.example.bookzone.ui.home.HomePage
import com.example.bookzone.ui.theme.BookZoneTheme
import com.example.bookzone.ui.wishlist.WishlistPage
import com.example.bookzone.utlis.models.NavItem

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            BookZoneTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    val navController = rememberNavController()
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
                HomePage(sheetState)
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
    FiltersBottomSheet(
        sheetState
    )

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