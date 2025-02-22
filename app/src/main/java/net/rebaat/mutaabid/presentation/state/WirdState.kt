package net.rebaat.mutaabid.presentation.state

import kotlinx.datetime.Clock
import kotlinx.datetime.LocalDate
import kotlinx.datetime.TimeZone
import kotlinx.datetime.toLocalDateTime
import kotlinx.datetime.todayIn
import net.rebaat.mutaabid.data.model.WirdItmam

data class WirdState(
    val selectedDate: LocalDate = Clock.System.todayIn(TimeZone.currentSystemDefault()),
    val isLoading: Boolean = false,
    val wirdItmams: List<WirdItmam> = emptyList(),
)
