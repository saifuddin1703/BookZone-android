package com.example.bookzone.ui.book.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.bookzone.model.Book
import com.example.bookzone.ui.book.UIState
import com.example.bookzone.ui.book.repository.BookRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class BookViewModel @Inject constructor(private val bookRepository: BookRepository): ViewModel() {

//    @Inject lateinit var bookRepository: BookRepository

    private val _state : MutableLiveData<UIState> by lazy {
        MutableLiveData(UIState.LOADING)
    }

    val state : LiveData<UIState> = _state

    suspend fun getBooks(options : Map<String,String>){
        _state.value = UIState.LOADING
        try {
            val response = bookRepository.getBooks(options = options)
            Log.d("TAG",response.toString())
            if (response.status == "success"){
                _state.value = UIState.SUCCESS(data = response.data)
            }else{
                throw Exception("Error getting books")
            }
        }catch (e : Exception){
            Log.d("ERROR",e.message.toString())
            _state.value = UIState.ERROR(e.message)
        }
    }
}