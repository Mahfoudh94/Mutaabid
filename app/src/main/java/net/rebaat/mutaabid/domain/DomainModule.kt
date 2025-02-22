package net.rebaat.mutaabid.domain

import net.rebaat.mutaabid.data.repository.ItmamRepository
import net.rebaat.mutaabid.data.repository.WirdRepository
import net.rebaat.mutaabid.domain.usecase.GetAllWirdsUseCase
import net.rebaat.mutaabid.domain.usecase.GetWirdItmamsOfDayUseCase
import net.rebaat.mutaabid.domain.usecase.InsertWirdUseCase
import net.rebaat.mutaabid.domain.usecase.UpsertItmamUseCase
import org.koin.dsl.module

fun provideGetAllWirdsUseCase(wirdRepository: WirdRepository): GetAllWirdsUseCase {
    return GetAllWirdsUseCase(wirdRepository)
}
fun provideInsertWirdUseCase(wirdRepository: WirdRepository): InsertWirdUseCase {
    return InsertWirdUseCase(wirdRepository)
}
fun provideUpsertItmamUseCase(itmamRepository: ItmamRepository): UpsertItmamUseCase {
    return UpsertItmamUseCase(itmamRepository)
}

fun provideWirdItmamOfDayUseCase(
    wirdRepository: WirdRepository
): GetWirdItmamsOfDayUseCase {
    return GetWirdItmamsOfDayUseCase(wirdRepository)
}

val domainModule =  module {
    single { provideGetAllWirdsUseCase(get()) }
    single { provideInsertWirdUseCase(get()) }
    single { provideUpsertItmamUseCase(get()) }
    single { provideWirdItmamOfDayUseCase(get()) }
}