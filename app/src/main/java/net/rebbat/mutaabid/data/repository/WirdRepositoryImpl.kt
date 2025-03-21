package net.rebbat.mutaabid.data.repository

import android.icu.util.IslamicCalendar
import kotlinx.coroutines.flow.Flow
import net.rebbat.mutaabid.data.dao.WirdDao
import net.rebbat.mutaabid.data.model.Wird
import net.rebbat.mutaabid.data.model.WirdItmam
import net.rebbat.mutaabid.defaultZone

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