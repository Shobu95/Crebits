package com.shobu95.crebits.screens.about

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.result.contract.ActivityResultContracts
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import com.shobu95.crebits.R
import com.shobu95.crebits.databinding.FragmentAboutBinding

class About : Fragment() {

    private lateinit var binding: FragmentAboutBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(
            inflater,
            R.layout.fragment_about,
            container,
            false
        )

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.ivProfilePhoto.setOnClickListener {
            getPicture.launch("image/*")
        }
    }

    private val getPicture = registerForActivityResult(ActivityResultContracts.GetContent()) {
        it?.let { uri ->
            binding.ivProfilePhoto.setImageURI(uri)
        }
    }
}