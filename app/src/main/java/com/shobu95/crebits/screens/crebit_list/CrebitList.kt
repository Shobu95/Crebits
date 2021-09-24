package com.shobu95.crebits.screens.crebit_list

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import com.shobu95.crebits.R
import com.shobu95.crebits.database.TransactionDatabase
import com.shobu95.crebits.database.TransactionDatabaseDao
import com.shobu95.crebits.database.entities.Transaction
import com.shobu95.crebits.databinding.FragmentCrebitListBinding
import com.shobu95.crebits.utils.dialogs.TwoButtonDialogFragment
import com.shobu95.crebits.utils.showSnackBar

class CrebitList : Fragment() {

    private lateinit var binding: FragmentCrebitListBinding
    private lateinit var database: TransactionDatabaseDao
    private lateinit var viewModelFactory: CrebitListViewModelFactory
    private lateinit var viewModel: CrebitListViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(
            inflater,
            R.layout.fragment_crebit_list,
            container,
            false
        )

        setupDatabase()
        setupViewModel()
        setupViewLifeCycle()
        setupCrebitList()
        setupSnackBar()
        navigateToAddEditCrebitScreen()

        return binding.root
    }

    private fun setupDatabase() {
        val application = requireNotNull(this.activity).application
        database = TransactionDatabase.getInstance(application).transactionDatabaseDao
    }

    private fun setupViewModel() {
        viewModelFactory = CrebitListViewModelFactory(database)
        viewModel = ViewModelProvider(this, viewModelFactory)
            .get(CrebitListViewModel::class.java)
    }

    private fun setupViewLifeCycle() {
        binding.apply {
            listViewModel = viewModel
            lifecycleOwner = viewLifecycleOwner
        }
    }

    private fun setupCrebitList() {
        val adapter = CrebitListAdapter(CrebitListListener {
            if (it != null) {
                val direction = CrebitListDirections.actionCrebitListToAddEditCrebit(it)
                this.findNavController().navigate(direction)
            }
        }, DeleteCrebitListener {
            showDeleteDialog(it)
        })
        binding.rvCrebits.adapter = adapter

        viewModel.transactions.observe(viewLifecycleOwner, {
            it?.let {
                adapter.submitList(it as MutableList<Transaction>)
            }
        })
    }

    private fun showDeleteDialog(transaction: Transaction): Boolean {
        TwoButtonDialogFragment(
            getString(R.string.delete_message),
            getString(R.string.yes),
            getString(R.string.no),
            viewModel.setDeleteDialogListener(transaction)
        ).show(parentFragmentManager, "deleteDialog")
        return true
    }

    private fun setupSnackBar() {
        viewModel.showDeleteSnackBar.observe(viewLifecycleOwner, {
            if (it == true) {
                view?.showSnackBar(getString(R.string.delete_crebit_message))
                viewModel.onSnackBarShown()
            }

        })
    }

    private fun navigateToAddEditCrebitScreen() {
        binding.fabAddCrebit.setOnClickListener {
            val direction = CrebitListDirections.actionCrebitListToAddEditCrebit(null)
            it.findNavController().navigate(direction)
        }
    }



}