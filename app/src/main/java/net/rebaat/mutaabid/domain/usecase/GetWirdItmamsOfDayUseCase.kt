package net.rebaat.mutaabid.domain.usecase

import net.rebaat.mutaabid.data.model.WirdItmam
import net.rebaat.mutaabid.data.repository.WirdItmamRepository
import java.util.Date

class GetWirdItmamsOfDayUseCase(
    private val wirdItmamRepository: WirdItmamRepository
) {
    operator fun invoke(date: Date): List<WirdItmam> {
        return wirdItmamRepository.getWirdItmams(date)
    }
}