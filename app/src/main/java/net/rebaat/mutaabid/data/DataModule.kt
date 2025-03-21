package net.rebaat.mutaabid.data

import android.app.Application
import android.util.Log
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import kotlinx.coroutines.DelicateCoroutinesApi
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import net.rebaat.mutaabid.data.dao.ItmamDao
import net.rebaat.mutaabid.data.dao.WirdDao
import net.rebaat.mutaabid.data.model.Wird
import net.rebaat.mutaabid.data.repository.ItmamRepository
import net.rebaat.mutaabid.data.repository.ItmamRepositoryImpl
import net.rebaat.mutaabid.data.repository.WirdRepository
import net.rebaat.mutaabid.data.repository.WirdRepositoryImpl
import org.koin.dsl.module
import java.util.concurrent.Executors

@OptIn(DelicateCoroutinesApi::class)
fun provideDatabase(application: Application): AppDatabase {
    return Room.databaseBuilder(
        context = application, name = "database", klass = AppDatabase::class.java
    ).fallbackToDestructiveMigration().setQueryCallback(
        { sqlQuery, bindArgs ->
            Log.d("DB", "SQL Query: $sqlQuery SQL Args: $bindArgs")
        }, executor = Executors.newSingleThreadExecutor()
    ).addCallback(object : RoomDatabase.Callback() {
        override fun onCreate(db: SupportSQLiteDatabase) {
            super.onCreate(db)
            GlobalScope.launch {

                val defaultWirds = listOf(
                    Wird(name = "QuranRead", isAvailable = true, icon = R.drawable.quran),
                    Wird(name = "QuranListen", isAvailable = true, icon = R.drawable.quran_hear),
                    Wird(name = "NiyahRenew", isAvailable = true, icon = R.drawable.niyah),
                    Wird(name = "Subh", isAvailable = true, icon = R.drawable.prayer),
                    Wird(name = "AdhkarSabah", isAvailable = true, icon = R.drawable.adhkar),
                    Wird(name = "Dhuha", isAvailable = true, icon = R.drawable.praying),
                    Wird(name = "Fasting", isAvailable = true, icon = R.drawable.fasting),
                    Wird(name = "Sadaka", isAvailable = true, icon = R.drawable.sadaka),
                    Wird(name = "Kahf", isAvailable = true, icon = R.drawable.kahf),
                    Wird(name = "PreDuhr", isAvailable = true, icon = R.drawable.praying),
                    Wird(name = "Duhr", isAvailable = true, icon = R.drawable.prayer),
                    Wird(name = "PostDuhr", isAvailable = true, icon = R.drawable.praying),
                    Wird(name = "Asr", isAvailable = true, icon = R.drawable.prayer),
                    Wird(name = "AdhkarMasa", isAvailable = true, icon = R.drawable.adhkar),
                    Wird(name = "DuaaMaghrib", isAvailable = true, icon = R.drawable.duaa),
                    Wird(name = "Maghrib", isAvailable = true, icon = R.drawable.prayer),
                    Wird(name = "PostMaghrib", isAvailable = true, icon = R.drawable.praying),
                    Wird(name = "Isha", isAvailable = true, icon = R.drawable.prayer),
                    Wird(name = "Witr", isAvailable = true, icon = R.drawable.praying),
                    Wird(name = "Kiyam", isAvailable = true, icon = R.drawable.kiyam),
                    Wird(name = "AdhkarNaum", isAvailable = true, icon = R.drawable.adhkar),
                    Wird(name = "TaharaNaum", isAvailable = true, icon = R.drawable.tahara),
                    Wird(name = "IslamKnowledge", isAvailable = true, icon = R.drawable.islam_knowledge),
                )

                provideDatabase(application).getWirdDao().insertAll(wirds = defaultWirds)

            }
        }
    }).build()
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