package net.rebaat.mutaabid.domain.usecase

import net.rebaat.mutaabid.data.model.Wird
import net.rebaat.mutaabid.data.repository.WirdRepository

class ToggleWirdVisibilityUseCase(
    private val wirdRepository: WirdRepository
) {
    suspend operator fun invoke(wird: Wird, isVisible: Boolean? = null) {
        val newVisiblity = when (isVisible) {
        null -> !wird.isAvailable!!
        else -> isVisible
    }
        wirdRepository.upsertWird(
            wird.copy(
                isAvailable = newVisiblity
            )
        )
    }
}