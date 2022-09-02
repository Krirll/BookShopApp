package ru.krirll.testtask.presentation.viewModels.uiState

import ru.krirll.domain.entities.ImageItem

sealed class BookDetailsUiState {
    data class Success(val books: List<ImageItem>): BookDetailsUiState()
    data class Error(val message: String): BookDetailsUiState()
    object Empty: BookDetailsUiState()
}