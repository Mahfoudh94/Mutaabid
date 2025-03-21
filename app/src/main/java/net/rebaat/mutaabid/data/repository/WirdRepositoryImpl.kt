package net.rebaat.mutaabid.data.repository

import android.icu.util.IslamicCalendar
import kotlinx.coroutines.flow.Flow
import kotlinx.datetime.Clock
import kotlinx.datetime.LocalDate
import kotlinx.datetime.TimeZone
import kotlinx.datetime.todayIn
import net.rebaat.mutaabid.data.dao.WirdDao
import net.rebaat.mutaabid.data.model.Wird
import net.rebaat.mutaabid.data.model.WirdItmam
import net.rebaat.mutaabid.defaultZone

class WirdRepositoryImpl(private val wirdDao: WirdDao): WirdRepository {
    override fun getAllWirds(): Flow<List<Wird>> {
        return wirdDao.getAll()
    }

    override fun getAllWirdsOfDay(date: IslamicCalendar?): Flow<List<WirdItmam>> {
        return wirdDao.getWirdItmams(
            date ?: IslamicCalendar().defaultZone()
        )
    }

    override suspend fun upsertWird(wird: Wird): Boolean {
        try {
            wirdDao.upsert(wird)
            return true
        } catch (e: Exception) {
            return false
        }
    }
}