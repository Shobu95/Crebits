package com.shobu95.crebits.backend.modules

import android.app.Application
import android.content.Context
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

/**
 * @author AliAzazAlam on 10/29/2021.
 */
@Module
class ApplicationModule {

    @Provides
    @Singleton
    fun providesApplicationContext(application: Application): Context = application

}