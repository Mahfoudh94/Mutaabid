package net.rebaat.mutaabid.data.repository

import kotlinx.coroutines.flow.Flow
import net.rebaat.mutaabid.data.model.Itmam

interface ItmamRepository {
    fun getAllItmams(): Flow<List<Itmam>>
    suspend fun upsertItmam(itmam: Itmam): Boolean
}