package net.rebaat.mutaabid.presentation.viewmodel

import androidx.compose.runtime.MutableState
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import net.rebaat.mutaabid.data.model.Wird
import net.rebaat.mutaabid.domain.usecase.GetAllWirdsUseCase
import net.rebaat.mutaabid.domain.usecase.InsertWirdUseCase
import net.rebaat.mutaabid.presentation.state.WirdState

class WirdViewModel(
    private val getAllWirdsUseCase: GetAllWirdsUseCase,
    private val insertWirdUseCase: InsertWirdUseCase,
): ViewModel() {
    private val _wirdState = MutableStateFlow(WirdState())

    val wirdState: StateFlow<WirdState> = _wirdState.asStateFlow()

    init {
        getAllWirds()
    }

    fun getAllWirds() {
        viewModelScope.launch {
            _wirdState.value = _wirdState.value.copy(
                isLoading = true
            )
            _wirdState.value = _wirdState.value.copy(
                wirds = getAllWirdsUseCase.invoke(),
                isLoading = false
            )
        }
    }
    suspend fun insertWird(wird: Wird) = insertWirdUseCase.invoke(wird)
}