package net.rebaat.mutaabid

import android.app.Application
import net.rebaat.mutaabid.data.dataModule
import net.rebaat.mutaabid.domain.domainModule
import net.rebaat.mutaabid.presentation.presentationModule
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