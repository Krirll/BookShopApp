package ru.krirll.testtask.presentation.fragments

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.fragment.findNavController
import com.google.android.material.snackbar.Snackbar
import kotlinx.coroutines.launch
import ru.krirll.testtask.MainApplication
import ru.krirll.testtask.databinding.FragmentCarouselBestSellersBinding
import ru.krirll.testtask.presentation.listAdapters.BestSellerAdapter
import ru.krirll.testtask.presentation.listAdapters.ImagesAdapter
import ru.krirll.testtask.presentation.viewModels.uiState.BooksUiState
import ru.krirll.testtask.presentation.viewModels.CarouselBestSellerViewModel
import ru.krirll.testtask.presentation.viewModels.uiState.CarouselUiState
import ru.krirll.testtask.presentation.viewModels.ViewModelFactory
import javax.inject.Inject

class CarouselBestSellersFragment : Fragment() {

    @Inject
    lateinit var viewModelFactory: ViewModelFactory

    private val carouselBestSellerViewModel by lazy {
        ViewModelProvider(this, viewModelFactory)[CarouselBestSellerViewModel::class.java]
    }

    private var _viewBinding: FragmentCarouselBestSellersBinding? = null
    private val viewBinding: FragmentCarouselBestSellersBinding
        get() = _viewBinding ?: throw RuntimeException("FragmentCarouselBestSellersBinding == null")

    private var carouselAdapter: ImagesAdapter? = null
    private var bestSellerAdapter: BestSellerAdapter? = null

    override fun onAttach(context: Context) {
        (requireActivity().application as MainApplication).component.inject(this)
        super.onAttach(context)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _viewBinding = FragmentCarouselBestSellersBinding.inflate(inflater, container, false)
        return viewBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        inject()
        initCarousel()
        initBestSellerBooks()
        observeViewModel()
        getContent()
    }

    private fun inject() {
        (requireActivity().application as MainApplication).component.inject(this)
    }

    private fun getContent() {
        carouselBestSellerViewModel.apply {
            getCarousel()
            getBestSellerBooks()
        }
    }

    private fun observeViewModel() {
        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                carouselBestSellerViewModel.carousel.collect {
                    when (it) {
                        is CarouselUiState.Success -> {
                            carouselAdapter?.submitList(it.carousel)
                        }
                        is CarouselUiState.Error -> {
                            showShackBar(it.message)
                        }
                        is CarouselUiState.Empty -> Unit
                    }
                }
            }
        }

        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                carouselBestSellerViewModel.books.collect {
                    when (it) {
                        is BooksUiState.Success -> {
                            bestSellerAdapter?.submitList(it.books)
                        }
                        is BooksUiState.Error -> {
                            showShackBar(it.message)
                        }
                        is BooksUiState.Empty -> Unit
                    }
                }
            }
        }
    }

    private fun showShackBar(message: String) {
        view?.let { view ->
            Snackbar.make(
                view,
                message,
                Snackbar.LENGTH_LONG
            ).apply {
                animationMode = Snackbar.ANIMATION_MODE_SLIDE
            }.show()
        }
    }

    private fun initBestSellerBooks() {
        bestSellerAdapter = BestSellerAdapter().apply {
            setOnClickListener {
                findNavController().navigate(
                    CarouselBestSellersFragmentDirections.actionCarouselBestSellersFragmentToBookDetailsFragment(
                        it
                    )
                )
            }
        }
        viewBinding.bestSellerRecyclerView.adapter = bestSellerAdapter
    }

    private fun initCarousel() {
        carouselAdapter = ImagesAdapter()
        viewBinding.carouselRecyclerView.adapter = carouselAdapter
    }
}