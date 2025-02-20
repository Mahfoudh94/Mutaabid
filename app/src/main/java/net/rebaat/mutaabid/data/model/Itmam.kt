package net.rebaat.mutaabid.data.model

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey
import java.sql.Date

@Entity(foreignKeys = [
    ForeignKey(
        entity = Wird::class,
        parentColumns = ["id"],
        childColumns = ["wirdId"]
    )
])
data class Itmam(
    @PrimaryKey(autoGenerate = true) val id: Int,
    val wirdId: Int,

    val date: String,
    val done: Boolean
)
