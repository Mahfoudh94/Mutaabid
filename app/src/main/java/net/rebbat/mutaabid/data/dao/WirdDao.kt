package net.rebbat.mutaabid.data.dao

import android.icu.util.IslamicCalendar
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Transaction
import androidx.room.Upsert
import kotlinx.coroutines.flow.Flow
import net.rebbat.mutaabid.data.model.Wird
import net.rebbat.mutaabid.data.model.WirdItmam

@Dao
interface WirdDao {
    @Upsert
    suspend fun upsert(wird: Wird)

    @Delete
    suspend fun delete(wird: Wird)

    @Insert
    suspend fun insertAll(wirds: List<Wird>)

    @Query("SELECT * FROM wird")
    fun getAll(): Flow<List<Wird>>

    @Transaction
    @Query("SELECT *, itmam.itmamId as itmam_itmamId," +
            " itmam.wirdId as itmam_wirdId," +
            " itmam.date as itmam_date," +
            " itmam.done as itmam_done FROM wird" +
            " LEFT JOIN itmam ON wird.id = itmam.wirdId AND itmam.date = :date")
    fun getWirdItmams(date: IslamicCalendar): Flow<List<WirdItmam>>
}