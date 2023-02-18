package com.example.bookzone.ui.book

import android.provider.Contacts.Intents.UI
import com.example.bookzone.model.Book

sealed class UIState(val isLoading : Boolean,val data: List<Book>?, val message: String?){
    object LOADING : UIState(true,null,null)
    class SUCCESS(data: List<Book>?) : UIState(false,data = data,null)
    class ERROR(message: String?) : UIState(false,data = null,message)
}