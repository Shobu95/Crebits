package com.shobu95.crebits

import android.app.Application
import android.content.Context
import com.example.vehicleapp.di.components.DaggerAppComponent
import dagger.android.AndroidInjector
import dagger.android.DispatchingAndroidInjector
import dagger.android.HasAndroidInjector
import javax.inject.Inject

/**
 * @author AliAzazAlam on 10/29/2021.
 */
class Crebits: Application(), HasAndroidInjector {

    @Inject
    lateinit var injector: DispatchingAndroidInjector<Any>

    override fun onCreate() {
        super.onCreate()

        DaggerAppComponent.builder()
            .create(this)
            .build()
            .inject(this)

        application = this
    }

    override fun androidInjector(): AndroidInjector<Any> {
        return injector
    }

    companion object {
        var application: Crebits? = null
            private set

        @JvmStatic
        val context: Context
            get() = application!!.applicationContext
    }
}