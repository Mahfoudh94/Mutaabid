package net.rebbat.mutaabid.domain.usecase

import kotlinx.coroutines.flow.Flow
import net.rebbat.mutaabid.data.model.Wird
import net.rebbat.mutaabid.data.repository.WirdRepository

class GetAllWirdsUseCase(private val wirdRepository: WirdRepository) {
    suspend operator fun invoke(): Flow<List<Wird>> {
        return wirdRepository.getAllWirds()
    }
}