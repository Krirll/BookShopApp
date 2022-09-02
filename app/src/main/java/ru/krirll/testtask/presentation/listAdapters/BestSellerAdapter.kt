package ru.krirll.testtask.presentation.listAdapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import com.bumptech.glide.Glide
import ru.krirll.testtask.R
import ru.krirll.testtask.databinding.BestSellerItemBinding
import ru.krirll.domain.entities.BookItem

class BestSellerAdapter : ListAdapter<BookItem, BookItemViewHolder>(BookItemDiffCallBack()) {

    private var click: ((BookItem) -> Unit)? = null

    fun setOnClickListener(listener: ((BookItem) -> Unit)) {
        click = listener
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BookItemViewHolder =
        BookItemViewHolder(
            BestSellerItemBinding.inflate(
                LayoutInflater.from(parent.context)
            )
        )

    override fun onBindViewHolder(holder: BookItemViewHolder, position: Int) {
        holder.binding.apply {
            //set text
            titleTextView.text = currentList[position].title
            authorTextView.text = currentList[position].author
            priceTextView.text =
                holder.itemView.context.getString(R.string.price, currentList[position].price)
            scoreTextView.text = currentList[position].score.plus(" ")
            amountTextView.text =
                holder.itemView.context.getString(R.string.amount, currentList[position].amount)
            //set image
            Glide.with(holder.itemView.context)
                .load(currentList[position].imageUrl)
                .useAnimationPool(true)
                .centerCrop()
                .into(bestSellerImageView)
            //click listener
            val item = getItem(position)
            holder.binding.bookItem.setOnClickListener {
                click?.invoke(item)
            }
        }
    }
}