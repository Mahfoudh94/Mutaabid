package net.rebaat.mutaabid.data.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Query
import androidx.room.Upsert
import net.rebaat.mutaabid.data.model.Wird

@Dao
interface WirdDao {
    @Upsert
    suspend fun upsert(wird: Wird)

    @Delete
    suspend fun delete(wird: Wird)

    @Query("SELECT * FROM wird")
    fun getAll(): List<Wird>
}