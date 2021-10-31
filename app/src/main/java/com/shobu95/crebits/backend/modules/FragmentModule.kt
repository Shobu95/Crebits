package com.shobu95.crebits.backend.modules

import com.shobu95.crebits.screens.add_edit_crebit.AddEditCrebit
import com.shobu95.crebits.screens.crebit_list.CrebitList
import dagger.Module
import dagger.android.ContributesAndroidInjector

/**
 * @author AliAzazAlam on 10/29/2021.
 */
@Module
abstract class FragmentModule {

    @ContributesAndroidInjector
    abstract fun bindCrebitListFragment(): CrebitList

    @ContributesAndroidInjector
    abstract fun bindAddEditCrebitFragment(): AddEditCrebit
}