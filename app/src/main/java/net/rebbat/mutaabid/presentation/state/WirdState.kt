package net.rebbat.mutaabid.presentation.state

import android.icu.util.IslamicCalendar
import net.rebbat.mutaabid.data.model.WirdItmam
import net.rebbat.mutaabid.defaultZone

data class WirdState(
    val selectedDate: IslamicCalendar = IslamicCalendar().defaultZone(),
    val isLoading: Boolean = false,
    val wirdItmams: List<WirdItmam> = emptyList(),
)
