package ru.krirll.testtask.presentation.viewModels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import ru.krirll.testtask.domain.useCases.GetBestSellerBooksUseCase
import ru.krirll.testtask.domain.useCases.GetCarouselImagesUseCase
import ru.krirll.testtask.presentation.viewModels.uiState.BooksUiState
import ru.krirll.testtask.presentation.viewModels.uiState.CarouselUiState
import javax.inject.Inject

//todo удалить ненужные комменты!!!

class CarouselBestSellerViewModel @Inject constructor(
    private val getBestSellerBooksUseCase: GetBestSellerBooksUseCase,
    private val getCarouselImagesUseCase: GetCarouselImagesUseCase
) : ViewModel() {

    private var _carousel = MutableStateFlow<CarouselUiState>(CarouselUiState.Empty)
    val carousel: StateFlow<CarouselUiState> = _carousel

    private var _books = MutableStateFlow<BooksUiState>(BooksUiState.Empty)
    val books: StateFlow<BooksUiState> = _books

    fun getCarousel() {
        viewModelScope.launch {
            try {
                _carousel.value = CarouselUiState.Success(getCarouselImagesUseCase())
            } catch (ex: Exception) {
                _carousel.value = CarouselUiState.Error(ex.message ?: "")
            }
        }
    }

    fun getBestSellerBooks() {
        viewModelScope.launch {
            try {
                _books.value = BooksUiState.Success(getBestSellerBooksUseCase())
            } catch (ex: Exception) {
                _books.value = BooksUiState.Error(ex.message ?: "")
            }
        }
    }
}