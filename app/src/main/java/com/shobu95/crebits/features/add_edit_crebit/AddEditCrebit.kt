package com.shobu95.crebits.features.add_edit_crebit

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.shobu95.crebits.R
import com.shobu95.crebits.databinding.FragmentAddEditCrebitBinding
import com.shobu95.crebits.databinding.FragmentCrebitListBinding

class AddEditCrebit : Fragment() {

    private lateinit var binding: FragmentAddEditCrebitBinding
    private lateinit var viewModel: AddEditCrebitViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(inflater ,R.layout.fragment_add_edit_crebit, container, false)
        viewModel = ViewModelProvider(this).get(AddEditCrebitViewModel::class.java)
        binding.viewModel = viewModel
        binding.lifecycleOwner = this.viewLifecycleOwner


        viewModel.transactionType.observe(viewLifecycleOwner, {
            Log.d("Crebit", "Type: $it}")
        })

        return binding.root
    }

}