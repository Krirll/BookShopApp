package ru.krirll.testtask.presentation.viewModels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import ru.krirll.domain.useCases.GetSimilarBooksUseCase
import ru.krirll.testtask.presentation.viewModels.uiState.BookDetailsUiState

class BookDetailsViewModel (
    private val getSimilarBooksUseCase: GetSimilarBooksUseCase
): ViewModel() {

    private var _similarBooks = MutableStateFlow<BookDetailsUiState>(BookDetailsUiState.Empty)
    val similarBooks: StateFlow<BookDetailsUiState> = _similarBooks

    fun getSimilarBooks() {
        viewModelScope.launch {
            try {
                _similarBooks.value = BookDetailsUiState.Success(getSimilarBooksUseCase())
            } catch (ex: Exception) {
                _similarBooks.value = BookDetailsUiState.Error(ex.message ?: "")
            }
        }
    }
}