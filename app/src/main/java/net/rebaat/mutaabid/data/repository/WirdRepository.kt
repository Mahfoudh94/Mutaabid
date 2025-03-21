package net.rebaat.mutaabid.data.repository

import android.icu.util.IslamicCalendar
import kotlinx.coroutines.flow.Flow
import net.rebaat.mutaabid.data.model.Wird
import net.rebaat.mutaabid.data.model.WirdItmam

interface WirdRepository {
    fun getAllWirds(): Flow<List<Wird>>
    fun getAllWirdsOfDay(date: IslamicCalendar?): Flow<List<WirdItmam>>
    suspend fun upsertWird(wird: Wird): Boolean
}