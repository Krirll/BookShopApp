package ru.krirll.testtask.presentation.listAdapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import com.bumptech.glide.Glide
import ru.krirll.testtask.databinding.CarouselItemBinding
import ru.krirll.testtask.databinding.SimilarItemBinding
import ru.krirll.domain.entities.ImageItem

class ImagesAdapter : ListAdapter<ImageItem, ImageItemViewHolder>(ImageItemDiffCallBack()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ImageItemViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding =
            when (viewType) {
                CAROUSEL -> CarouselItemBinding.inflate(layoutInflater)
                SIMILAR -> SimilarItemBinding.inflate(layoutInflater)
                else -> throw RuntimeException("Unknown view type $viewType")
            }
        return ImageItemViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ImageItemViewHolder, position: Int) {
        Glide.with(holder.itemView.context)
            .load(currentList[position].imageUrl)
            .centerCrop()
            .into(
                when (holder.binding) {
                    is CarouselItemBinding -> holder.binding.carouselImageView
                    is SimilarItemBinding -> holder.binding.similarImageView
                    else -> throw RuntimeException("Unknown binding")
                }
            )
    }

    override fun getItemViewType(position: Int): Int {
        return when (currentList[position].isCarousel) {
            true -> CAROUSEL
            false -> SIMILAR
        }
    }

    companion object {
        private const val CAROUSEL = 0
        private const val SIMILAR = 1
    }
}