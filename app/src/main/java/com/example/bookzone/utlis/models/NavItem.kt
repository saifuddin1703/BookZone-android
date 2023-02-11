package com.example.bookzone.utlis.models

import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.painterResource
import com.example.bookzone.R

enum class NavItem(val title : String, val iconId : Int) {
    Home("Home", iconId = R.drawable.home_icon),
    Categories("Categories", iconId = R.drawable.categories_icon),
    Wishlist("Wishlist", iconId = R.drawable.wishlist_icon),
    Cart("My Cart", iconId = R.drawable.cart_icon),
}