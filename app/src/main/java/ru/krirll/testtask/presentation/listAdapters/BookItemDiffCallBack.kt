package ru.krirll.testtask.presentation.listAdapters

import androidx.recyclerview.widget.DiffUtil
import ru.krirll.domain.entities.BookItem

class BookItemDiffCallBack: DiffUtil.ItemCallback<BookItem>() {

    override fun areItemsTheSame(oldItem: BookItem, newItem: BookItem): Boolean {
        return oldItem.imageUrl == newItem.imageUrl
    }

    override fun areContentsTheSame(oldItem: BookItem, newItem: BookItem): Boolean {
        return oldItem == newItem
    }

}
