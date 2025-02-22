package net.rebaat.mutaabid.presentation.action

import kotlinx.datetime.LocalDate
import net.rebaat.mutaabid.data.model.WirdItmam

sealed interface WirdItmamAction {
    data class ToggleItmamWird(val wirdItmam: WirdItmam) : WirdItmamAction
    data class SelectDate(val selectedDate: LocalDate) : WirdItmamAction
}