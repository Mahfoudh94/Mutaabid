package net.rebbat.mutaabid.presentation.viewmodel

import android.icu.util.IslamicCalendar
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import net.rebbat.mutaabid.data.model.Itmam
import net.rebbat.mutaabid.data.model.Wird
import net.rebbat.mutaabid.data.model.WirdItmam
import net.rebbat.mutaabid.domain.usecase.GetWirdItmamsOfDayUseCase
import net.rebbat.mutaabid.domain.usecase.ToggleWirdVisibilityUseCase
import net.rebbat.mutaabid.domain.usecase.UpsertItmamUseCase
import net.rebbat.mutaabid.presentation.action.WirdItmamAction

class WirdViewModel(
    private val getAllWirdOfDayUseCase: GetWirdItmamsOfDayUseCase,
    private val upsertItmamUseCase: UpsertItmamUseCase,
    private val toggleWirdVisibilityUseCase: ToggleWirdVisibilityUseCase,
) : ViewModel() {
    private val _selectedDate = MutableStateFlow(IslamicCalendar())
    val selectedDate = _selectedDate.asStateFlow()

    val wirdItmams = _selectedDate.flatMapLatest { date ->
        getAllWirdOfDayUseCase(date)
    }.stateIn(viewModelScope, SharingStarted.Lazily, emptyList())

    fun onAction(action: WirdItmamAction) {
        when (action) {
            is WirdItmamAction.ToggleItmamWird -> toggleItmam(action.wirdItmam)
            is WirdItmamAction.SelectDate -> selectDate(action.selectedDate)
            is WirdItmamAction.ToggleWirdVisibility -> toggleWirdVisibility(
                action.wird,
                action.visibility
            )
        }
    }

    private fun toggleItmam(wirdItmam: WirdItmam) {
        val itmam = if (wirdItmam.itmam == null)
            Itmam(
                wirdId = wirdItmam.wird.id,
                date = _selectedDate.value,
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

    private fun selectDate(date: IslamicCalendar) {
        _selectedDate.value = date
    }

    private fun toggleWirdVisibility(wird: Wird, visible: Boolean?) {
        viewModelScope.launch {
            toggleWirdVisibilityUseCase(wird)
        }
    }
}