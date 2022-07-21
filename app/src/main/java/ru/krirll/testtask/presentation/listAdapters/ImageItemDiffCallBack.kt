package ru.krirll.testtask.presentation.listAdapters

import androidx.recyclerview.widget.DiffUtil
import ru.krirll.testtask.domain.entities.ImageItem

class ImageItemDiffCallBack: DiffUtil.ItemCallback<ImageItem>() {

    override fun areItemsTheSame(oldItem: ImageItem, newItem: ImageItem): Boolean {
        return oldItem.imageUrl == newItem.imageUrl
    }

    override fun areContentsTheSame(oldItem: ImageItem, newItem: ImageItem): Boolean {
        return oldItem == newItem
    }

}
