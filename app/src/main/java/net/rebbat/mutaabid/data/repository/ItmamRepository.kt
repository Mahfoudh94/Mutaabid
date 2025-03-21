package net.rebbat.mutaabid.data.repository

import kotlinx.coroutines.flow.Flow
import net.rebbat.mutaabid.data.model.Itmam

interface ItmamRepository {
    fun getAllItmams(): Flow<List<Itmam>>
    suspend fun upsertItmam(itmam: Itmam): Boolean
}