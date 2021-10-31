package com.shobu95.crebits.screens.crebit_list

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import com.shobu95.crebits.R
import com.shobu95.crebits.backend.repository.ResultCallBack
import com.shobu95.crebits.base.FragmentBase
import com.shobu95.crebits.model.TransactionData
import com.shobu95.crebits.databinding.FragmentCrebitListBinding
import com.shobu95.crebits.utils.Constants
import com.shobu95.crebits.utils.DialogButtonListener
import com.shobu95.crebits.utils.TwoButtonDialogFragment
import com.shobu95.crebits.utils.showSnackBar

class CrebitList : FragmentBase() {

    private lateinit var binding: FragmentCrebitListBinding
    private val viewModel: CrebitListViewModel by lazy {
        ViewModelProvider(this, viewModelFactory)
            .get(CrebitListViewModel::class.java)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(
            inflater,
            R.layout.fragment_crebit_list,
            container,
            false
        )

        setupViewLifeCycle()
        setupCrebitList()
        setupSnackBar()
        navigateToAddEditCrebitScreen()

        return binding.root
    }

    private fun setupViewLifeCycle() {
        binding.apply {
            listViewModel = viewModel
            lifecycleOwner = viewLifecycleOwner
        }
    }

    private fun setupCrebitList() {
        val adapter = CrebitListAdapter(CrebitListListener {
            val direction = CrebitListDirections.actionCrebitListToAddEditCrebit(
                it,
                getString(R.string.edit_crebit)
            )
            this.findNavController().navigate(direction)
        }, DeleteCrebitListener {
            showDeleteDialog(it)
        })
        binding.rvCrebits.adapter = adapter

        viewModel.allTransaction.observe(viewLifecycleOwner, {
            when (it) {
                is ResultCallBack.CallException -> {
                }
                is ResultCallBack.Error -> {
                }
                is ResultCallBack.Success -> {
                    adapter.submitList(it.data)
                }
            }
        })
    }

    private fun showDeleteDialog(transactionData: TransactionData): Boolean {
        TwoButtonDialogFragment(
            getString(R.string.delete_message),
            getString(R.string.yes),
            getString(R.string.no),
            object : DialogButtonListener {
                override fun onPositiveClicked() {
                    viewModel.deleteTransaction(transactionData)
                }

                override fun onNegativeClicked() {
                    // just close dialog
                }

            }
        ).show(parentFragmentManager, Constants.DELETE_DIALOG)
        return true
    }

    private fun setupSnackBar() {
        viewModel.showDeleteSnackBar.observe(viewLifecycleOwner, {
            if (it == true) {
                view?.showSnackBar(getString(R.string.delete_crebit_message))
                viewModel.showDeleteSnackBar.value = false
            }
        })
    }

    private fun navigateToAddEditCrebitScreen() {
        binding.fabAddCrebit.setOnClickListener {
            val direction = CrebitListDirections.actionCrebitListToAddEditCrebit(
                null,
                getString(R.string.add_crebit)
            )
            it.findNavController().navigate(direction)
        }
    }

}