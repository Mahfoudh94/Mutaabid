package net.rebbat.mutaabid

import android.app.Application
import net.rebbat.mutaabid.data.dataModule
import net.rebbat.mutaabid.domain.domainModule
import net.rebbat.mutaabid.presentation.presentationModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class KoinApplication: Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(this@KoinApplication)
            modules(
                dataModule,
                domainModule,
                presentationModule,
            )
        }

    }
}