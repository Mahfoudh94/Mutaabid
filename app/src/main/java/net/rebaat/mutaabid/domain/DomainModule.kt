package net.rebaat.mutaabid.domain

import net.rebaat.mutaabid.data.repository.WirdItmamRepository
import net.rebaat.mutaabid.data.repository.WirdRepository
import net.rebaat.mutaabid.domain.usecase.GetAllWirdsUseCase
import net.rebaat.mutaabid.domain.usecase.GetWirdItmamsOfDayUseCase
import net.rebaat.mutaabid.domain.usecase.InsertWirdUseCase
import org.koin.dsl.module

fun provideGetAllWirdsUseCase(wirdRepository: WirdRepository): GetAllWirdsUseCase {
    return GetAllWirdsUseCase(wirdRepository)
}
fun provideInsertWirdUseCase(wirdRepository: WirdRepository): InsertWirdUseCase {
    return InsertWirdUseCase(wirdRepository)
}

fun provideWirdItmamOfDayUseCase(
    wirdItmamRepository: WirdItmamRepository
): GetWirdItmamsOfDayUseCase {
    return GetWirdItmamsOfDayUseCase(wirdItmamRepository)
}

val domainModule =  module {
    single { provideGetAllWirdsUseCase(get()) }
    single { provideInsertWirdUseCase(get()) }
    single { provideWirdItmamOfDayUseCase(get()) }
}