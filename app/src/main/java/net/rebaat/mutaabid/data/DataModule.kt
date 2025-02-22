package net.rebaat.mutaabid.data

import android.app.Application
import android.util.Log
import androidx.room.Room
import androidx.room.RoomDatabase
import net.rebaat.mutaabid.data.dao.ItmamDao
import net.rebaat.mutaabid.data.dao.WirdDao
import net.rebaat.mutaabid.data.repository.ItmamRepository
import net.rebaat.mutaabid.data.repository.ItmamRepositoryImpl
import net.rebaat.mutaabid.data.repository.WirdRepository
import net.rebaat.mutaabid.data.repository.WirdRepositoryImpl
import org.koin.dsl.module
import java.util.concurrent.Executors

fun provideDatabase(application: Application): AppDatabase {
    return Room.databaseBuilder(
        context = application,
        name = "database",
        klass = AppDatabase::class.java
    )
        .fallbackToDestructiveMigration()
        .setQueryCallback(
            RoomDatabase.QueryCallback {
                sqlQuery, bindArgs ->
                    Log.d("DB", "SQL Query: $sqlQuery SQL Args: $bindArgs")
            }, executor = Executors.newSingleThreadExecutor()
        )
        .build()
}

fun provideWirdDao(database: AppDatabase): WirdDao {
    return database.getWirdDao()
}

fun provideItmamDao(database: AppDatabase): ItmamDao {
    return database.getItmamDao()
}

fun provideWirdRepository(wirdDao: WirdDao): WirdRepository {
    return WirdRepositoryImpl(wirdDao)
}

fun provideItmamRepository(wirdDao: ItmamDao): ItmamRepository {
    return ItmamRepositoryImpl(wirdDao)
}

val dataModule = module {
    single { provideDatabase(get()) }
    single { provideWirdDao(get()) }
    single { provideItmamDao(get()) }
    factory { provideWirdRepository(get()) }
    factory { provideItmamRepository(get()) }
}