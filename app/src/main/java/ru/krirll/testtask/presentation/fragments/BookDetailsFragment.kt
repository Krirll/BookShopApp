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
import androidx.navigation.fragment.navArgs
import com.bumptech.glide.Glide
import com.google.android.material.snackbar.Snackbar
import kotlinx.coroutines.launch
import ru.krirll.testtask.R
import ru.krirll.testtask.databinding.FragmentBookDetailsBinding
import ru.krirll.testtask.presentation.listAdapters.ImagesAdapter
import ru.krirll.testtask.presentation.viewModels.BookDetailsViewModel

class BookDetailsFragment : Fragment() {

    private val args by navArgs<BookDetailsFragmentArgs>()

    private val bookDetailsViewModel by lazy {
        ViewModelProvider(this)[BookDetailsViewModel::class.java]
    }

    private var _viewBinding: FragmentBookDetailsBinding? = null
    private val viewBinding: FragmentBookDetailsBinding
        get() = _viewBinding ?: throw RuntimeException("FragmentBookDetailsBinding == null")

    private var similarAdapter: ImagesAdapter? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _viewBinding = FragmentBookDetailsBinding.inflate(inflater, container, false)
        return viewBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initSimilarRecyclerView()
        initSelectedBook()
        setOnCloseButtonClickListener()
        observeViewModel()
        bookDetailsViewModel.getSimilarBooks()
    }

    private fun setOnCloseButtonClickListener() {
        viewBinding.closeButton.setOnClickListener {
            findNavController().popBackStack()
        }
    }

    private fun observeViewModel() {
        bookDetailsViewModel.similarBooks.observe(viewLifecycleOwner) {
            similarAdapter?.submitList(it)
        }
        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                bookDetailsViewModel.networkErrors.collect {
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

    private fun initSelectedBook() {
        viewBinding.apply {
            args.book.apply {
                Glide.with(this@BookDetailsFragment)
                    .load(imageUrl)
                    .centerCrop()
                    .into(bookImage)
                titleTextView.text = title
                authorTextView.text = author
                scoreTextView.text = score.plus(" ")
                amountTextView.text = getString(R.string.amount, amount)
                priceButton.text = getString(R.string.price, price)
            }
        }
    }

    private fun initSimilarRecyclerView() {
        similarAdapter = ImagesAdapter()
        viewBinding.similarBooksRecyclerView.adapter = similarAdapter
    }
}