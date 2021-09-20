package com.shobu95.crebits.screens.crebit_list

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import com.shobu95.crebits.R
import com.shobu95.crebits.database.TransactionDatabase
import com.shobu95.crebits.database.TransactionDatabaseDao
import com.shobu95.crebits.database.entities.Transaction
import com.shobu95.crebits.databinding.FragmentCrebitListBinding

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
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_crebit_list, container, false)

        setupDatabase()
        setupViewModel()
        setupViewLifeCycle()
        setupCrebitList()
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
        binding.viewModel = viewModel
        binding.lifecycleOwner = this.viewLifecycleOwner
    }

    private fun setupCrebitList() {
        val adapter = CrebitListAdapter(CrebitListListener {
            if (it != null) {
                Log.d("crebit", it.toString())
            }
        })
        binding.rvCrebits.adapter = adapter

        viewModel.transactions.observe(viewLifecycleOwner, {
            it?.let {
                adapter.submitList(it as MutableList<Transaction>)
            }
        })
    }

    private fun navigateToAddEditCrebitScreen() {
        binding.fabAddCrebit.setOnClickListener {
            it.findNavController().navigate(CrebitListDirections.actionCrebitListToAddEditCrebit())
        }

    }


}