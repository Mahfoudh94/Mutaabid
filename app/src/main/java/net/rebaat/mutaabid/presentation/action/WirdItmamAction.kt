package net.rebaat.mutaabid.presentation.action

import android.icu.util.IslamicCalendar
import net.rebaat.mutaabid.data.model.Wird
import net.rebaat.mutaabid.data.model.WirdItmam

sealed interface WirdItmamAction {
    data class ToggleWirdVisibility(val wird: Wird, val visibility: Boolean? = null) : WirdItmamAction
    data class ToggleItmamWird(val wirdItmam: WirdItmam) : WirdItmamAction
    data class SelectDate(val selectedDate: IslamicCalendar) : WirdItmamAction
}