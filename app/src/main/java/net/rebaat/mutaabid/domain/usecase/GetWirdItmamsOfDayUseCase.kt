package net.rebaat.mutaabid.domain.usecase

import kotlinx.datetime.LocalDate
import net.rebaat.mutaabid.data.model.Wird
import net.rebaat.mutaabid.data.model.WirdItmam
import net.rebaat.mutaabid.data.repository.WirdRepository

class GetWirdItmamsOfDayUseCase(
    private val wirdRepository: WirdRepository
) {
    suspend operator fun invoke(date: LocalDate?): List<WirdItmam> {
        return wirdRepository.getAllWirdsOfDay(date)
    }
}