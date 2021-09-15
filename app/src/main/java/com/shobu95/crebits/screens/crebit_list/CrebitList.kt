package com.shobu95.crebits.screens.crebit_list

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import com.shobu95.crebits.R
import com.shobu95.crebits.databinding.FragmentCrebitListBinding

class CrebitList : Fragment() {

    private lateinit var binding: FragmentCrebitListBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_crebit_list, container, false)

        binding.fabAddCrebit.setOnClickListener { v: View ->
            v.findNavController().navigate(CrebitListDirections.actionCrebitListToAddEditCrebit())
        }

        return binding.root;
    }

}