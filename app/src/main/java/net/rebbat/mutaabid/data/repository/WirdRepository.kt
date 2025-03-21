package net.rebbat.mutaabid.data.repository

import android.icu.util.IslamicCalendar
import kotlinx.coroutines.flow.Flow
import net.rebbat.mutaabid.data.model.Wird
import net.rebbat.mutaabid.data.model.WirdItmam

interface WirdRepository {
    fun getAllWirds(): Flow<List<Wird>>
    fun getAllWirdsOfDay(date: IslamicCalendar?): Flow<List<WirdItmam>>
    suspend fun upsertWird(wird: Wird): Boolean
}