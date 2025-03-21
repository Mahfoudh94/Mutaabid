package net.rebbat.mutaabid.domain

import net.rebbat.mutaabid.data.repository.ItmamRepository
import net.rebbat.mutaabid.data.repository.WirdRepository
import net.rebbat.mutaabid.domain.usecase.GetAllWirdsUseCase
import net.rebbat.mutaabid.domain.usecase.GetWirdItmamsOfDayUseCase
import net.rebbat.mutaabid.domain.usecase.InsertWirdUseCase
import net.rebbat.mutaabid.domain.usecase.ToggleWirdVisibilityUseCase
import net.rebbat.mutaabid.domain.usecase.UpsertItmamUseCase
import org.koin.core.module.dsl.singleOf
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

fun provideToggleWirdVisibilityUseCase(
    wirdRepository: WirdRepository
): ToggleWirdVisibilityUseCase {
    return ToggleWirdVisibilityUseCase(wirdRepository)
}

val domainModule =  module {
    singleOf(::provideGetAllWirdsUseCase)
    singleOf(::provideInsertWirdUseCase)
    singleOf(::provideUpsertItmamUseCase)
    singleOf(::provideWirdItmamOfDayUseCase)
    singleOf(::provideToggleWirdVisibilityUseCase)
}