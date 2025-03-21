package net.rebbat.mutaabid.data.model

import androidx.room.Embedded

data class WirdItmam(
    @Embedded val wird: Wird,
    @Embedded(prefix = "itmam_")
    val itmam: Itmam?,
)