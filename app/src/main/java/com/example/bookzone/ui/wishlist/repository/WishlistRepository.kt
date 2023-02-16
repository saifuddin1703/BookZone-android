package com.example.bookzone.ui.wishlist.repository

import com.example.bookzone.retrofit.services.WishlistService
import javax.inject.Inject

class WishlistRepository @Inject constructor(
    private val wishlistService: WishlistService
) {

}