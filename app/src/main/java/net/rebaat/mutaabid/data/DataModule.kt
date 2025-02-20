package net.rebaat.mutaabid.data

import android.app.Application
import androidx.room.Room
import net.rebaat.mutaabid.data.dao.ItmamDao
import net.rebaat.mutaabid.data.dao.WirdDao
import net.rebaat.mutaabid.data.dao.WirdItmamDao
import net.rebaat.mutaabid.data.repository.ItmamRepository
import net.rebaat.mutaabid.data.repository.ItmamRepositoryImpl
import net.rebaat.mutaabid.data.repository.WirdItmamRepository
import net.rebaat.mutaabid.data.repository.WirdItmamRepositoryImpl
import net.rebaat.mutaabid.data.repository.WirdRepository
import net.rebaat.mutaabid.data.repository.WirdRepositoryImpl
import org.koin.dsl.module

fun provideDatabase(application: Application): AppDatabase {
    return Room.databaseBuilder(
        context = application,
        name = "database",
        klass = AppDatabase::class.java
    )
        .allowMainThreadQueries()
        .build()
}

fun provideWirdDao(database: AppDatabase): WirdDao {
    return database.getWirdDao()
}

fun provideItmamDao(database: AppDatabase): ItmamDao {
    return database.getItmamDao()
}

fun provideWirdItmamDao(database: AppDatabase): WirdItmamDao {
    return database.getWirdItmamDao()
}

fun provideWirdRepository(wirdDao: WirdDao): WirdRepository {
    return WirdRepositoryImpl(wirdDao)
}

fun provideItmamRepository(wirdDao: ItmamDao): ItmamRepository {
    return ItmamRepositoryImpl(wirdDao)
}

fun provideWirdItmamRepository(wirdItmamDao: WirdItmamDao): WirdItmamRepository {
    return WirdItmamRepositoryImpl(wirdItmamDao)
}

val dataModule = module {
    single { provideDatabase(get()) }
    single { provideWirdDao(get()) }
    single { provideItmamDao(get()) }
    single { provideWirdItmamDao(get()) }
    factory { provideWirdRepository(get()) }
    factory { provideItmamRepository(get()) }
    factory { provideWirdItmamRepository(get()) }
}