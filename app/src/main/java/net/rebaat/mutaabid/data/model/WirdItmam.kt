package net.rebaat.mutaabid.data.model

import androidx.room.Embedded
import androidx.room.Relation

data class WirdItmam(
    @Embedded val wird: Wird,
    @Embedded(prefix = "itmam_")
    val itmam: Itmam?,
)