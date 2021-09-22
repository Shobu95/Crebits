package com.shobu95.crebits.screens.crebit_list

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.shobu95.crebits.R
import com.shobu95.crebits.database.entities.Transaction
import com.shobu95.crebits.databinding.CrebitListItemBinding
import com.shobu95.crebits.screens.crebit_list.CrebitListAdapter.ViewHolder
import com.shobu95.crebits.utils.enums.TransactionType

class CrebitListAdapter(
    private val clickListener: CrebitListListener
) :
    ListAdapter<Transaction, ViewHolder>(TransactionDiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder.from(parent)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = getItem(position)
        holder.bind(item, clickListener)
    }

    class ViewHolder private constructor(private val binding: CrebitListItemBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(item: Transaction, clickListener: CrebitListListener) {
            binding.apply {
                transaction = item
                itemClickListener = clickListener
                setTransactionIcon(item)
                executePendingBindings()
            }
        }

        private fun setTransactionIcon(item: Transaction) {
            if (item.type.equals(TransactionType.CREDIT.name)) {
                binding.imgTransaction.setImageResource(R.drawable.ic_credit_arrow)
            } else {
                binding.imgTransaction.setImageResource(R.drawable.ic_debit_arrow)
            }
        }

        companion object {
            fun from(parent: ViewGroup): ViewHolder {
                val layoutInflater = LayoutInflater.from(parent.context)
                val binding = CrebitListItemBinding.inflate(layoutInflater, parent, false)
                return ViewHolder(binding)
            }
        }
    }
}

class CrebitListListener(val clickListener: (transaction: Transaction) -> Unit) {
    fun onClick(transaction: Transaction) = transaction?.let { clickListener(it) }
}

class TransactionDiffCallback : DiffUtil.ItemCallback<Transaction>() {
    override fun areItemsTheSame(oldItem: Transaction, newItem: Transaction): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: Transaction, newItem: Transaction): Boolean {
        return oldItem == newItem
    }
}