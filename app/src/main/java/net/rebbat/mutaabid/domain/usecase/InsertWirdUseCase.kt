package net.rebbat.mutaabid.domain.usecase

import net.rebbat.mutaabid.data.model.Wird
import net.rebbat.mutaabid.data.repository.WirdRepository

class InsertWirdUseCase(private val wirdRepository: WirdRepository) {
    suspend operator fun invoke(wird: Wird): Boolean {
        return wirdRepository.upsertWird(wird)
    }
}