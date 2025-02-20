package net.rebaat.mutaabid.data

import androidx.room.Database
import androidx.room.RoomDatabase
import net.rebaat.mutaabid.data.dao.ItmamDao
import net.rebaat.mutaabid.data.dao.WirdDao
import net.rebaat.mutaabid.data.dao.WirdItmamDao
import net.rebaat.mutaabid.data.model.Itmam
import net.rebaat.mutaabid.data.model.Wird
import net.rebaat.mutaabid.data.model.WirdItmam

@Database(
    entities = [Wird::class, Itmam::class, WirdItmam::class],
    version = 1
)
abstract class AppDatabase: RoomDatabase() {
    abstract fun getWirdDao(): WirdDao
    abstract fun getItmamDao(): ItmamDao
    abstract fun getWirdItmamDao(): WirdItmamDao
}