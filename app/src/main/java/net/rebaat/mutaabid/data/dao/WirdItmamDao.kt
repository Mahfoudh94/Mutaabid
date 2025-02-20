package net.rebaat.mutaabid.data.dao

import androidx.room.Dao
import androidx.room.Query
import androidx.room.Upsert
import net.rebaat.mutaabid.data.model.WirdItmam

@Dao
interface WirdItmamDao {
    @Query("SELECT * FROM wird, itmam WHERE wird.id = itmam.wirdId")
    fun getAllWirdItmams(): List<WirdItmam>

    @Query("SELECT * FROM wird" +
            " LEFT JOIN itmam ON wird.id = itmam.wirdId" +
            " WHERE itmam.date = :date")
    fun getWirdItmams(date: String): List<WirdItmam>

    @Upsert
    suspend fun upsertWirdItmams(wirdItmams: List<WirdItmam>)
}