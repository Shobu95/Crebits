package com.shobu95.crebits.backend.modules

import androidx.lifecycle.ViewModelProvider
import com.shobu95.crebits.backend.factory.ViewModelFactory
import dagger.Binds
import dagger.Module

/**
 * @author AliAzazAlam on 10/29/2021.
 */
@Module
interface ViewModelFactoryModule {

    @Binds
    fun bindViewModelFactory(viewModelFactory: ViewModelFactory): ViewModelProvider.Factory

}