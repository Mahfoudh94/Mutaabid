package net.rebaat.mutaabid.data.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Query
import androidx.room.Upsert
import net.rebaat.mutaabid.data.model.Itmam

@Dao
interface ItmamDao {
    @Upsert
    suspend fun upsert(itmam: Itmam)

    @Delete
    suspend fun delete(itmam: Itmam)

    @Query("SELECT * FROM itmam ORDER BY date DESC")
    suspend fun getAll(): List<Itmam>
}