package ru.krirll.testtask.presentation.viewModels.uiState

import ru.krirll.domain.entities.BookItem

sealed class BooksUiState {
    data class Success(val books: List<BookItem>): BooksUiState()
    data class Error(val message: String): BooksUiState()
    object Empty: BooksUiState()
}