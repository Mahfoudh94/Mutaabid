package net.rebaat.mutaabid.presentation.state

import android.icu.util.IslamicCalendar
import android.icu.util.TimeZone as IcuTimeZone
import kotlinx.datetime.Clock
import kotlinx.datetime.LocalDate
import kotlinx.datetime.TimeZone
import kotlinx.datetime.todayIn
import net.rebaat.mutaabid.data.model.WirdItmam
import net.rebaat.mutaabid.defaultZone
import net.rebaat.mutaabid.toLocalDate

data class WirdState(
    val selectedDate: IslamicCalendar = IslamicCalendar().defaultZone(),
    val isLoading: Boolean = false,
    val wirdItmams: List<WirdItmam> = emptyList(),
)
