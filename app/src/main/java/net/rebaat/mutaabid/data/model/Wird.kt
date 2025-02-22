package net.rebaat.mutaabid.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Wird(
    @PrimaryKey(autoGenerate = true) val id: Int,

    val name: String = "",
    val isAvailable: Boolean? = true,
)
