package com.example.bookzone

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
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
import com.example.bookzone.ui.components.CustomTopAppBar
import com.example.bookzone.ui.theme.BookZoneTheme
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
                    LandingPage()
                }
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LandingPage(){
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
                mutableStateOf(1)
            }

            BottomAppBar(
                containerColor = Color.White,
                modifier =  Modifier.height(90.dp)
            ) {
                items.forEachIndexed {index,item->
                    NavigationBarItem(selected = selectedItem == index,
                        onClick = { 
                                  selectedItem = index
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
                                modifier = Modifier.size(33.dp),
                                contentDescription = "icon")
                        }
                    )
                }
            }
        }
    ){

        // container
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
        LandingPage()
    }
}