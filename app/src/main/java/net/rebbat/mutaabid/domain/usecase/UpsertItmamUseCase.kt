package net.rebbat.mutaabid.domain.usecase

import net.rebbat.mutaabid.data.model.Itmam
import net.rebbat.mutaabid.data.repository.ItmamRepository

class UpsertItmamUseCase(
    private val itmamRepository: ItmamRepository
) {
    suspend operator fun invoke(itmam: Itmam): Boolean {
        return itmamRepository.upsertItmam(itmam)
    }
}