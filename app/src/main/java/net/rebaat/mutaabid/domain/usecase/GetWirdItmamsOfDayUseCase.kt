package net.rebaat.mutaabid.domain.usecase

import android.icu.util.IslamicCalendar
import kotlinx.coroutines.flow.Flow
import kotlinx.datetime.LocalDate
import net.rebaat.mutaabid.data.model.WirdItmam
import net.rebaat.mutaabid.data.repository.WirdRepository

class GetWirdItmamsOfDayUseCase(
    private val wirdRepository: WirdRepository
) {
    operator fun invoke(date: IslamicCalendar?): Flow<List<WirdItmam>> {
        return wirdRepository.getAllWirdsOfDay(date)
    }
}