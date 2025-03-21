package net.rebbat.mutaabid.presentation.action

import android.icu.util.IslamicCalendar
import net.rebbat.mutaabid.data.model.Wird
import net.rebbat.mutaabid.data.model.WirdItmam

sealed interface WirdItmamAction {
    data class ToggleWirdVisibility(val wird: Wird, val visibility: Boolean? = null) : WirdItmamAction
    data class ToggleItmamWird(val wirdItmam: WirdItmam) : WirdItmamAction
    data class SelectDate(val selectedDate: IslamicCalendar) : WirdItmamAction
}