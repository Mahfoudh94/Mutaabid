package net.rebaat.mutaabid.data.repository

import kotlinx.datetime.LocalDate
import net.rebaat.mutaabid.data.model.Wird
import net.rebaat.mutaabid.data.model.WirdItmam

interface WirdRepository {
    suspend fun getAllWirds(): List<Wird>
    suspend fun getAllWirdsOfDay(date: LocalDate?): List<WirdItmam>
    suspend fun upsertWird(wird: Wird): Boolean
}