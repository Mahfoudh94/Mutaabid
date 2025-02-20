package net.rebaat.mutaabid.domain.usecase

import net.rebaat.mutaabid.data.model.Wird
import net.rebaat.mutaabid.data.repository.WirdRepository

class InsertWirdUseCase(private val wirdRepository: WirdRepository) {
    suspend operator fun invoke(wird: Wird): Boolean {
        return wirdRepository.upsertWird(wird)
    }
}