package com.example.bookzone.ui.categories

import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.ModalBottomSheetState
import androidx.compose.runtime.Composable
import com.example.bookzone.ui.components.BookList

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun BooksOfCategory(category: String,sheetState: ModalBottomSheetState){
    BookList(title = category, sheetState = sheetState)
}