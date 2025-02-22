package net.rebaat.mutaabid.data.repository

import net.rebaat.mutaabid.data.model.Itmam

interface ItmamRepository {
    suspend fun getAllItmams(): List<Itmam>
    suspend fun upsertItmam(itmam: Itmam): Boolean
}