package ru.krirll.testtask.presentation.viewModels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import ru.krirll.testtask.domain.entities.BookItem
import ru.krirll.testtask.domain.entities.ImageItem
import ru.krirll.testtask.domain.useCases.GetBestSellerBooksUseCase
import ru.krirll.testtask.domain.useCases.GetCarouselImagesUseCase
import javax.inject.Inject

class CarouselBestSellerViewModel @Inject constructor(
    private val getBestSellerBooksUseCase: GetBestSellerBooksUseCase/* = GetBestSellerBooksUseCase(repository)*/,
    private val getCarouselImagesUseCase: GetCarouselImagesUseCase/* = GetCarouselImagesUseCase(repository)*/
) : ViewModel() {

    private var _carousel = MutableLiveData<List<ImageItem>>()
    val carousel: LiveData<List<ImageItem>>
        get() = _carousel

    private var _bestSellerBooks = MutableLiveData<List<BookItem>>()
    val bestSellerBooks: LiveData<List<BookItem>>
        get() = _bestSellerBooks

    private var _networkErrors = Channel<Unit>(Channel.CONFLATED)
    val networkErrors: Flow<Unit>
        get() = _networkErrors.receiveAsFlow()

    fun getCarousel() {
        viewModelScope.launch {
            try {
                _carousel.value = getCarouselImagesUseCase()
            } catch (ex: Exception) {
                _networkErrors.send(Unit)
            }
        }
    }

    fun getBestSellerBooks() {
        viewModelScope.launch {
            try {
                _bestSellerBooks.value = getBestSellerBooksUseCase()
            } catch (ex: Exception) {
                _networkErrors.send(Unit)
            }
        }
    }
}