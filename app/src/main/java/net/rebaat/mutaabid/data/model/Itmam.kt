package net.rebaat.mutaabid.data.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey
import kotlinx.datetime.LocalDate

@Entity(
    foreignKeys = [
        ForeignKey(
            entity = Wird::class,
            parentColumns = ["id"],
            childColumns = ["wirdId"],
        )
    ]
)
data class Itmam(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "itmamId")
    val id: Int? = null,

    val wirdId: Int,

    val date: LocalDate,
    val done: Boolean
)
