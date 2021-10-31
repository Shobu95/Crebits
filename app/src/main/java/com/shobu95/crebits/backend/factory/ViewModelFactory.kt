package com.shobu95.crebits.backend.factory

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.shobu95.crebits.backend.repository.GeneralRepository
import com.shobu95.crebits.screens.add_edit_crebit.AddEditCrebitViewModel
import com.shobu95.crebits.screens.crebit_list.CrebitListViewModel
import javax.inject.Inject
import javax.inject.Singleton

/**
 * @author AliAzazAlam on 10/30/2021.
 */
@Singleton
@Suppress("UNCHECKED_CAST")
class ViewModelFactory @Inject constructor(private val repository: GeneralRepository) :
    ViewModelProvider.NewInstanceFactory() {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return when {
            modelClass.isAssignableFrom(CrebitListViewModel::class.java) -> CrebitListViewModel(
                repository
            ) as T
            modelClass.isAssignableFrom(AddEditCrebitViewModel::class.java) -> AddEditCrebitViewModel(
                repository
            ) as T
            else -> throw IllegalArgumentException("Unknown viewModel class $modelClass")
        }
    }

}