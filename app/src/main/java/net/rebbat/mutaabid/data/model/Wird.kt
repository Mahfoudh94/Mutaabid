package net.rebbat.mutaabid.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Wird(
    @PrimaryKey(autoGenerate = true) val id: Int? = null,

    val name: String = "",
    var isAvailable: Boolean? = true,
    val icon: Int? = null,
)
