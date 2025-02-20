package net.rebaat.mutaabid.data.model

import androidx.room.Embedded

data class WirdItmam(
    @Embedded val wird: Wird,
    @Embedded val itmam: Itmam,
)