package com.shobu95.crebits.screens.crebit_list

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.shobu95.crebits.R
import com.shobu95.crebits.model.TransactionData
import com.shobu95.crebits.databinding.CrebitListItemBinding
import com.shobu95.crebits.screens.crebit_list.CrebitListAdapter.ViewHolder
import com.shobu95.crebits.utils.Constants.TransactionType

class CrebitListAdapter(
    private val clickListener: CrebitListListener,
    private val deleteListener: DeleteCrebitListener
) :
    ListAdapter<TransactionData, ViewHolder>(TransactionDiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder.from(parent)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = getItem(position)
        holder.bind(item, clickListener, deleteListener)
    }

    class ViewHolder private constructor(private val binding: CrebitListItemBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(
            item: TransactionData,
            clickListener: CrebitListListener,
            deleteListener: DeleteCrebitListener
        ) {
            binding.apply {
                transaction = item
                itemClickListener = clickListener
                itemLongClickListener = deleteListener
                setTransactionIcon(item)
                executePendingBindings()
            }
        }

        private fun setTransactionIcon(item: TransactionData) {
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

class CrebitListListener(val clickListener: (transactionData: TransactionData) -> Unit) {
    fun onClick(transactionData: TransactionData) = transactionData?.let { clickListener(it) }
}

class DeleteCrebitListener(val longClickListener: (transactionData: TransactionData) -> Boolean) {
    fun onLongClick(transactionData: TransactionData) = transactionData?.let { longClickListener(it) }
}

class TransactionDiffCallback : DiffUtil.ItemCallback<TransactionData>() {
    override fun areItemsTheSame(oldItem: TransactionData, newItem: TransactionData): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: TransactionData, newItem: TransactionData): Boolean {
        return oldItem == newItem
    }
}