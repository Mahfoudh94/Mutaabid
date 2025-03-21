package net.rebbat.mutaabid.domain.usecase

import android.icu.util.IslamicCalendar
import kotlinx.coroutines.flow.Flow
import net.rebbat.mutaabid.data.model.WirdItmam
import net.rebbat.mutaabid.data.repository.WirdRepository

class GetWirdItmamsOfDayUseCase(
    private val wirdRepository: WirdRepository
) {
    operator fun invoke(date: IslamicCalendar?): Flow<List<WirdItmam>> {
        return wirdRepository.getAllWirdsOfDay(date)
    }
}