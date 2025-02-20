package net.rebaat.mutaabid.data.repository

import net.rebaat.mutaabid.data.dao.WirdDao
import net.rebaat.mutaabid.data.model.Wird

class WirdRepositoryImpl(private val wirdDao: WirdDao): WirdRepository {
    override fun getAllWirds(): List<Wird> {
        return wirdDao.getAll()
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