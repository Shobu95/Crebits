package com.shobu95.crebits.screens.add_edit_crebit

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.google.android.material.snackbar.Snackbar
import com.shobu95.crebits.R
import com.shobu95.crebits.database.TransactionDatabase
import com.shobu95.crebits.database.TransactionDatabaseDao
import com.shobu95.crebits.databinding.FragmentAddEditCrebitBinding
import com.shobu95.crebits.utils.dialogs.DatePickerFragment
import com.shobu95.crebits.utils.dialogs.TimePickerFragment

class AddEditCrebit : Fragment() {

    private lateinit var binding: FragmentAddEditCrebitBinding
    private lateinit var viewModelFactory: AddEditCrebitViewModelFactory
    private lateinit var database: TransactionDatabaseDao
    private lateinit var viewModel: AddEditCrebitViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = DataBindingUtil
            .inflate(inflater, R.layout.fragment_add_edit_crebit, container, false)

        val application = requireNotNull(this.activity).application
        database = TransactionDatabase.getInstance(application).transactionDatabaseDao

        viewModelFactory = AddEditCrebitViewModelFactory(database)
        viewModel = ViewModelProvider(this, viewModelFactory)
            .get(AddEditCrebitViewModel::class.java)

        binding.viewModel = viewModel
        binding.lifecycleOwner = this.viewLifecycleOwner

        setDatePickerObserver()
        setTimePickerObserver()
        setNavigateToList()
        setupSnackBarEvent()

        return binding.root
    }

    private fun setDatePickerObserver() {
        viewModel.openDatePicker.observe(viewLifecycleOwner, {
            if (it)
                DatePickerFragment(viewModel.setDatePickerListener()).show(
                    parentFragmentManager,
                    "datePicker"
                )
        })
    }

    private fun setTimePickerObserver() {
        viewModel.openTimePicker.observe(viewLifecycleOwner, {
            if (it)
                TimePickerFragment(viewModel.setTimePickerListener()).show(
                    parentFragmentManager,
                    "timePicker"
                )
        })
    }

    private fun setNavigateToList() {
        viewModel.navigateToList.observe(viewLifecycleOwner, {
            if (it == true) {
                this.findNavController().popBackStack()
                viewModel.onNavigateToListScreenComplete()
            }
        })
    }

    private fun setupSnackBarEvent() {
        viewModel.showSnackBarEvent.observe(viewLifecycleOwner, {
            if (it == true) {
                Snackbar.make(
                    requireActivity().findViewById(android.R.id.content),
                    getString(R.string.saved_message),
                    Snackbar.LENGTH_LONG
                ).show()
                viewModel.doneShowingSnackbar()
            }
        })
    }

}