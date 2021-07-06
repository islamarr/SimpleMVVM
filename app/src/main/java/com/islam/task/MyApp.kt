package com.islam.task

import android.app.Application
import com.islam.task.data.network.internet.ConnectivityInterCeptorImpl
import com.islam.task.data.network.MyTaskApi
import com.islam.task.data.repositories.ManufacturerRepository
import com.islam.task.generalUtils.MyTaskParameters
import com.islam.task.ui.manufacturer.ManufacturerViewModelFactory
import org.kodein.di.Kodein
import org.kodein.di.KodeinAware
import org.kodein.di.android.androidCoreModule
import org.kodein.di.generic.bind
import org.kodein.di.generic.instance
import org.kodein.di.generic.provider
import org.kodein.di.generic.singleton
import timber.log.Timber

class MyApp : Application(), KodeinAware {

    override val kodein = Kodein.lazy {
        import(androidCoreModule(this@MyApp))
        //initiate Api Service Class
        bind() from singleton { MyTaskApi(instance()) }
        bind() from singleton { ConnectivityInterCeptorImpl(instance()) }
        bind() from singleton { ManufacturerRepository(instance()) }
        bind() from singleton { MyTaskParameters(instance()) }

        bind() from provider { ManufacturerViewModelFactory(instance()) }
    }

    override fun onCreate() {
        super.onCreate()

        Timber.plant(Timber.DebugTree())
    }
}