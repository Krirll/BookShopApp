package ru.krirll.testtask.presentation.viewModels.uiState

import ru.krirll.domain.entities.ImageItem

sealed class CarouselUiState {
    data class Success(val carousel: List<ImageItem>): CarouselUiState()
    data class Error(val message: String): CarouselUiState()
    object Empty: CarouselUiState()
}
