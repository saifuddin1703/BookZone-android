package com.example.bookzone.di.module

import com.example.bookzone.retrofit.Retrofit
import com.example.bookzone.retrofit.services.*
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityComponent


@InstallIn(ActivityComponent::class)
object AppModule {

    private val retrofit = Retrofit.getInstance()

    @Provides
    fun provideBookService(): BookService {
        return retrofit.create(BookService::class.java)
    }

    @Provides
    fun provideCartService(): CartService {
        return retrofit.create(CartService::class.java)
    }

    @Provides
    fun provideMeService(): MeService {
       return retrofit.create(MeService::class.java)
    }

    @Provides
    fun provideUserService(): UserService {
        return retrofit.create(UserService::class.java)
    }

    @Provides
    fun provideWishlistService(): WishlistService {
        return retrofit.create(WishlistService::class.java)
    }

}