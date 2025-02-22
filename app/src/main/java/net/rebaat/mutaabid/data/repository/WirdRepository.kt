package net.rebaat.mutaabid.data.repository

import kotlinx.coroutines.flow.Flow
import kotlinx.datetime.LocalDate
import net.rebaat.mutaabid.data.model.Wird
import net.rebaat.mutaabid.data.model.WirdItmam

interface WirdRepository {
    fun getAllWirds(): Flow<List<Wird>>
    fun getAllWirdsOfDay(date: LocalDate?): Flow<List<WirdItmam>>
    suspend fun upsertWird(wird: Wird): Boolean
}