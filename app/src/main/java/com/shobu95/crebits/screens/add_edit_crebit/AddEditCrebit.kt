package com.shobu95.crebits.screens.add_edit_crebit

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.shobu95.crebits.R
import com.shobu95.crebits.database.TransactionDatabase
import com.shobu95.crebits.database.TransactionDatabaseDao
import com.shobu95.crebits.databinding.FragmentAddEditCrebitBinding
import com.shobu95.crebits.utils.Constants
import com.shobu95.crebits.utils.DatePickerFragment
import com.shobu95.crebits.utils.TimePickerFragment
import com.shobu95.crebits.utils.showSnackBar

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
        binding = DataBindingUtil.inflate(
            inflater,
            R.layout.fragment_add_edit_crebit,
            container,
            false
        )

        setupDatabase()
        setupViewModel()
        setupViewLifeCycle()
        setupScreenState()
        setDatePickerObserver()
        setTimePickerObserver()
        setNavigateToList()
        setupSnackBarEvent()
        setupBtnExecute()

        return binding.root
    }

    private fun setupBtnExecute() {
        binding.btnExecute.setOnClickListener {
            if (validateForm())
                viewModel.onExecute()
        }
    }

    private fun validateForm(): Boolean {

        if(viewModel.transactionType.value==null){
            binding.rbDebit.error = getString(R.string.error_message, "Option")
            binding.errorText.text = getString(R.string.error_message, "Option")
            binding.errorText.visibility = View.VISIBLE
            return false
        } else {
            binding.rbDebit.error = null
            binding.errorText.visibility = View.GONE
        }

        if(viewModel.amount.value==null){
            binding.etAmount.error = getString(R.string.error_message, "Amount")
            return false
        } else
            binding.etAmount.error = null

        if(viewModel.date.value==null){
            binding.etDate.error = getString(R.string.error_message, "Date")
            return false
        }else
            binding.etDate.error = null

        if(viewModel.time.value==null){
            binding.etTime.error = getString(R.string.error_message, "Time")
            return false
        }else
            binding.etTime.error = null

        if(viewModel.description.value==null){
            binding.etDescription.error = getString(R.string.error_message, "Description")
            return false
        }else
            binding.etDescription.error = null

        return true
    }

    private fun setupDatabase() {
        val application = requireNotNull(this.activity).application
        database = TransactionDatabase.getInstance(application).transactionDatabaseDao
    }

    private fun setupViewModel() {
        val arguments = AddEditCrebitArgs.fromBundle(requireArguments())
        viewModelFactory = AddEditCrebitViewModelFactory(arguments.transaction, database)
        viewModel = ViewModelProvider(this, viewModelFactory)
            .get(AddEditCrebitViewModel::class.java)
    }

    private fun setupViewLifeCycle() {
        binding.viewModel = viewModel
        binding.lifecycleOwner = this.viewLifecycleOwner
    }

    private fun setupScreenState() {
        viewModel.screenState.observe(viewLifecycleOwner, {
            if (it.equals(Constants.SCREEN_STATE_ADD)) {
                setAddScreen()
            } else {
                setEditScreen()
            }
        })
    }

    private fun setAddScreen() {
        binding.btnExecute.text = Constants.SAVE
    }

    private fun setEditScreen() {
        binding.btnExecute.text = Constants.UPDATE
    }

    private fun setDatePickerObserver() {
        viewModel.openDatePicker.observe(viewLifecycleOwner, {
            if (it)
                DatePickerFragment(viewModel.setDatePickerListener()).show(
                    parentFragmentManager,
                    Constants.DATE_PICKER
                )
        })
    }

    private fun setTimePickerObserver() {
        viewModel.openTimePicker.observe(viewLifecycleOwner, {
            if (it)
                TimePickerFragment(viewModel.setTimePickerListener()).show(
                    parentFragmentManager,
                    Constants.TIME_PICKER
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
            if (it.equals(Constants.SCREEN_STATE_ADD)) {
                view?.showSnackBar(getString(R.string.add_crebit_message))
            } else {
                view?.showSnackBar(getString(R.string.edit_crebit_message))
            }

        })
    }

}