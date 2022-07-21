package ru.krirll.testtask.presentation.viewModels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import ru.krirll.testtask.data.repository.RepositoryImpl
import ru.krirll.testtask.domain.entities.ImageItem
import ru.krirll.testtask.domain.useCases.GetSimilarBooksUseCase

class BookDetailsViewModel(
    private val repository: RepositoryImpl = RepositoryImpl(),
    private val getSimilarBooksUseCase: GetSimilarBooksUseCase = GetSimilarBooksUseCase(repository)
): ViewModel() {

    private var _similarBooks = MutableLiveData<List<ImageItem>>()
    val similarBooks: LiveData<List<ImageItem>>
        get() = _similarBooks

    private var _networkErrors = Channel<Unit>(Channel.CONFLATED)
    val networkErrors: Flow<Unit>
        get() = _networkErrors.receiveAsFlow()

    fun getSimilarBooks() {
        viewModelScope.launch {
            try {
                _similarBooks.value = getSimilarBooksUseCase()
            } catch (ex: Exception) {
                _networkErrors.send((Unit))
            }
        }
    }
}