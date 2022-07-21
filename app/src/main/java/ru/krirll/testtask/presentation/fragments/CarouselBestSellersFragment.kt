package ru.krirll.testtask.presentation.fragments

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
import ru.krirll.testtask.R
import ru.krirll.testtask.databinding.FragmentCarouselBestSellersBinding
import ru.krirll.testtask.presentation.listAdapters.BestSellerAdapter
import ru.krirll.testtask.presentation.listAdapters.ImagesAdapter
import ru.krirll.testtask.presentation.viewModels.CarouselBestSellerViewModel

class CarouselBestSellersFragment : Fragment() {

    private val carouselBestSellerViewModel by lazy {
        ViewModelProvider(this)[CarouselBestSellerViewModel::class.java]
    }

    private var _viewBinding: FragmentCarouselBestSellersBinding? = null
    private val viewBinding: FragmentCarouselBestSellersBinding
        get() = _viewBinding ?: throw RuntimeException("FragmentCarouselBestSellersBinding == null")

    private var carouselAdapter: ImagesAdapter? = null
    private var bestSellerAdapter: BestSellerAdapter? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _viewBinding = FragmentCarouselBestSellersBinding.inflate(inflater, container, false)
        return viewBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initCarousel()
        initBestSellerBooks()
        observeViewModel()
        getContent()
    }

    private fun getContent() {
        carouselBestSellerViewModel.apply {
            getCarousel()
            getBestSellerBooks()
        }
    }

    private fun observeViewModel() {
        carouselBestSellerViewModel.carousel.observe(viewLifecycleOwner) {
            carouselAdapter?.submitList(it)
        }
        carouselBestSellerViewModel.bestSellerBooks.observe(viewLifecycleOwner) {
            bestSellerAdapter?.submitList(it)
        }
        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                carouselBestSellerViewModel.networkErrors.collect {
                    view?.let {
                        Snackbar.make(
                            it,
                            getString(R.string.network_error),
                            Snackbar.LENGTH_LONG
                        ).apply {
                            animationMode = Snackbar.ANIMATION_MODE_SLIDE
                        }.show()
                    }
                }
            }
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