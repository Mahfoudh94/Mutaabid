package net.rebbat.mutaabid.data.model

import android.icu.util.IslamicCalendar
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey

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

    val wirdId: Int?,

    val date: IslamicCalendar,
    val done: Boolean
)
