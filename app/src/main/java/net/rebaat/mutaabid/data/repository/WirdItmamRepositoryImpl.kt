package net.rebaat.mutaabid.data.repository

import net.rebaat.mutaabid.data.dao.WirdItmamDao
import net.rebaat.mutaabid.data.model.WirdItmam
import java.util.Date

class WirdItmamRepositoryImpl(
    private val wirdItmamDao: WirdItmamDao
): WirdItmamRepository {
    override fun getWirdItmams(date: Date?): List<WirdItmam> {
        val _date = date ?: Date()
        return wirdItmamDao.getWirdItmams(_date.toString())
    }

    override suspend fun submitWirdItmams(wirdItmams: List<WirdItmam>): Boolean {
        try {
            wirdItmamDao.upsertWirdItmams(wirdItmams)
            return true
        } catch (e: Exception) {
            return false
        }
    }

}