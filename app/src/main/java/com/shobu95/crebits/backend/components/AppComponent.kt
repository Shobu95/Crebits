package com.example.vehicleapp.di.components

import android.app.Application
import com.shobu95.crebits.Crebits
import com.shobu95.crebits.backend.modules.*
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjectionModule
import dagger.android.AndroidInjector
import javax.inject.Singleton

@Singleton
@Component(
    modules = [
        AndroidInjectionModule::class,
        ApplicationModule::class,
        DatabaseModule::class,
        FragmentModule::class,
        GeneralRepositoryModule::class,
        ViewModelFactoryModule::class
    ]
)
interface AppComponent : AndroidInjector<Crebits> {

    @Component.Builder
    interface Builder {
        @BindsInstance
        fun create(app: Application): Builder

        fun build(): AppComponent
    }

    override fun inject(app: Crebits)
}