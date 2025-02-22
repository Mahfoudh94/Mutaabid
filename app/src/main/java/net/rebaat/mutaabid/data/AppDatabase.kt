package net.rebaat.mutaabid.data

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverter
import androidx.room.TypeConverters
import kotlinx.datetime.LocalDate
import kotlinx.datetime.format
import net.rebaat.mutaabid.data.dao.ItmamDao
import net.rebaat.mutaabid.data.dao.WirdDao
import net.rebaat.mutaabid.data.model.Itmam
import net.rebaat.mutaabid.data.model.Wird

class DateConverter {
    val dateFormat = LocalDate.Formats.ISO

    @TypeConverter
    fun dateToString(date: LocalDate): String {
        return date.format(dateFormat)
    }

    @TypeConverter
    fun stringToDate(dateString: String): LocalDate {
        return LocalDate.parse(dateString, dateFormat)
    }
}

@Database(
    entities = [Wird::class, Itmam::class],
    version = 3
)
@TypeConverters(DateConverter::class)
abstract class AppDatabase: RoomDatabase() {
    abstract fun getWirdDao(): WirdDao
    abstract fun getItmamDao(): ItmamDao

//    abstract fun getWirdItmamDao(): WirdItmamDao
}