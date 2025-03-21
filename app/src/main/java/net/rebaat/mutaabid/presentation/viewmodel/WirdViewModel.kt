package net.rebaat.mutaabid.presentation.viewmodel

import android.icu.util.IslamicCalendar
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.launch
import kotlinx.datetime.DateTimeUnit
import kotlinx.datetime.LocalDate
import kotlinx.datetime.plus
import net.rebaat.mutaabid.data.model.Itmam
import net.rebaat.mutaabid.data.model.Wird
import net.rebaat.mutaabid.data.model.WirdItmam
import net.rebaat.mutaabid.domain.usecase.GetWirdItmamsOfDayUseCase
import net.rebaat.mutaabid.domain.usecase.ToggleWirdVisibilityUseCase
import net.rebaat.mutaabid.domain.usecase.UpsertItmamUseCase
import net.rebaat.mutaabid.presentation.action.WirdItmamAction
import net.rebaat.mutaabid.presentation.state.WirdState

class WirdViewModel(
    private val getAllWirdOfDayUseCase: GetWirdItmamsOfDayUseCase,
    private val upsertItmamUseCase: UpsertItmamUseCase,
    private val toggleWirdVisibilityUseCase: ToggleWirdVisibilityUseCase,
) : ViewModel() {
    var state by mutableStateOf(WirdState())
        private set

    init {
        state = state.copy(isLoading = true)
        getAllWirdItmams()
    }

    fun onAction(action: WirdItmamAction) {
        when (action) {
            is WirdItmamAction.ToggleItmamWird -> toggleItmam(action.wirdItmam)
            is WirdItmamAction.SelectDate -> selectDate(action.selectedDate)
            is WirdItmamAction.ToggleWirdVisibility -> toggleWirdVisibility(action.wird, action.visibility)
        }
    }

    private fun getAllWirdItmams(selectedDate: IslamicCalendar? = null) {
        viewModelScope.launch {
            getAllWirdOfDayUseCase(selectedDate).catch {
                state = state.copy(
                    isLoading = false,
                )
            }.collect { newWirdItmams ->
                state = state.copy(
                    wirdItmams = newWirdItmams,
                    isLoading = false,
                )
            }
        }
    }

    private fun toggleItmam(wirdItmam: WirdItmam) {
        val itmam = if (wirdItmam.itmam == null)
            Itmam(
                wirdId = wirdItmam.wird.id,
                date = state.selectedDate,
                done = true,
            )
        else
            wirdItmam.itmam.copy(
                done = !wirdItmam.itmam.done,
            )
        viewModelScope.launch {
            upsertItmamUseCase(itmam)
        }
    }

    private fun selectDate(selectedDate: IslamicCalendar) {
        state = state.copy(
            selectedDate = selectedDate,
//            isLoading = true
        )
        getAllWirdItmams(selectedDate)
    }

    private fun toggleWirdVisibility(wird: Wird, visible: Boolean?) {
        viewModelScope.launch {
            toggleWirdVisibilityUseCase(wird)
        }
    }
}