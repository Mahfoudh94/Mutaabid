package net.rebaat.mutaabid.data.repository

import net.rebaat.mutaabid.data.model.Wird

interface WirdRepository {
    fun getAllWirds(): List<Wird>
    suspend fun upsertWird(wird: Wird): Boolean
}