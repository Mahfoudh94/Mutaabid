package net.rebaat.mutaabid.data

import android.icu.util.IslamicCalendar
import androidx.room.TypeConverter
import net.rebaat.mutaabid.DAY
import net.rebaat.mutaabid.MONTH
import net.rebaat.mutaabid.YEAR


class IslamicCalendarConverter {@TypeConverter
    fun dateToString(date: IslamicCalendar): String {
        val day = date.DAY.toString().padStart(2, '0')
        val month = date.MONTH.toString().padStart(2, '0')
        val year = date.YEAR.toString().padStart(2, '0')
        return "$day-$month-$year"
    }

    @TypeConverter
    fun stringToDate(dateString: String): IslamicCalendar {
        val day = dateString.substring(0, 2).toInt()
        val month = dateString.substring(3, 5).toInt()
        val year = dateString.substring(6, 8).toInt()
        return IslamicCalendar(year, month, day)
    }
}