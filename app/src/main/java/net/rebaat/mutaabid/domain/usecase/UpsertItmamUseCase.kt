package net.rebaat.mutaabid.domain.usecase

import kotlinx.coroutines.delay
import net.rebaat.mutaabid.data.model.Itmam
import net.rebaat.mutaabid.data.repository.ItmamRepository

class UpsertItmamUseCase(
    private val itmamRepository: ItmamRepository
) {
    suspend operator fun invoke(itmam: Itmam): Boolean {
        return itmamRepository.upsertItmam(itmam)
    }
}