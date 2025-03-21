package net.rebaat.mutaabid.data

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import net.rebaat.mutaabid.data.dao.ItmamDao
import net.rebaat.mutaabid.data.dao.WirdDao
import net.rebaat.mutaabid.data.model.Itmam
import net.rebaat.mutaabid.data.model.Wird

@Database(
    entities = [Wird::class, Itmam::class],
    version = 3
)
@TypeConverters(IslamicCalendarConverter::class)
abstract class AppDatabase: RoomDatabase() {
    abstract fun getWirdDao(): WirdDao
    abstract fun getItmamDao(): ItmamDao
}