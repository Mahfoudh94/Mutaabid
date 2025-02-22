package net.rebaat.mutaabid.domain.usecase

import kotlinx.coroutines.flow.Flow
import net.rebaat.mutaabid.data.model.Wird
import net.rebaat.mutaabid.data.repository.WirdRepository

class GetAllWirdsUseCase(private val wirdRepository: WirdRepository) {
    suspend operator fun invoke(): Flow<List<Wird>> {
        return wirdRepository.getAllWirds()
    }
}