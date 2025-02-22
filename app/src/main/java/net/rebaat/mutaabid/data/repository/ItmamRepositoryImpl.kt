package net.rebaat.mutaabid.data.repository

import kotlinx.coroutines.flow.Flow
import net.rebaat.mutaabid.data.dao.ItmamDao
import net.rebaat.mutaabid.data.model.Itmam

class ItmamRepositoryImpl(private val itmamDao: ItmamDao): ItmamRepository {
    override fun getAllItmams(): Flow<List<Itmam>> {
        return itmamDao.getAll()
    }
    override suspend fun upsertItmam(itmam: Itmam): Boolean {
        try {
            itmamDao.upsert(itmam)
            return true
        } catch (e: Exception) {
            return false
        }
    }
}