package net.rebaat.mutaabid.presentation.state

import net.rebaat.mutaabid.data.model.Wird

data class WirdState(
    val isLoading: Boolean = false,
    val wirds: List<Wird> = emptyList(),
)
