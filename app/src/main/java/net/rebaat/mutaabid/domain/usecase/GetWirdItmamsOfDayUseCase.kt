package net.rebaat.mutaabid.domain.usecase

import kotlinx.coroutines.flow.Flow
import kotlinx.datetime.LocalDate
import net.rebaat.mutaabid.data.model.WirdItmam
import net.rebaat.mutaabid.data.repository.WirdRepository

class GetWirdItmamsOfDayUseCase(
    private val wirdRepository: WirdRepository
) {
    suspend operator fun invoke(date: LocalDate?): Flow<List<WirdItmam>> {
        return wirdRepository.getAllWirdsOfDay(date)
    }
}