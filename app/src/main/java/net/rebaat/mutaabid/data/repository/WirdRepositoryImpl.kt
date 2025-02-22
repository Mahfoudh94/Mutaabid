package net.rebaat.mutaabid.data.repository

import kotlinx.coroutines.flow.Flow
import kotlinx.datetime.Clock
import kotlinx.datetime.LocalDate
import kotlinx.datetime.TimeZone
import kotlinx.datetime.format
import kotlinx.datetime.todayIn
import net.rebaat.mutaabid.data.dao.WirdDao
import net.rebaat.mutaabid.data.model.Wird
import net.rebaat.mutaabid.data.model.WirdItmam
import java.util.Date

class WirdRepositoryImpl(private val wirdDao: WirdDao): WirdRepository {
    override suspend fun getAllWirds(): Flow<List<Wird>> {
        return wirdDao.getAll()
    }

    override suspend fun getAllWirdsOfDay(date: LocalDate?): Flow<List<WirdItmam>> {
        return wirdDao.getWirdItmams(
            date ?: Clock.System.todayIn(TimeZone.currentSystemDefault())
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