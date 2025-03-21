package net.rebbat.mutaabid.data.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Query
import androidx.room.Upsert
import kotlinx.coroutines.flow.Flow
import net.rebbat.mutaabid.data.model.Itmam

@Dao
interface ItmamDao {
    @Upsert
    suspend fun upsert(itmam: Itmam)

    @Delete
    suspend fun delete(itmam: Itmam)

    @Query("SELECT * FROM itmam ORDER BY date DESC")
    fun getAll(): Flow<List<Itmam>>
}