package net.rebaat.mutaabid.presentation

import net.rebaat.mutaabid.domain.DefaultNavigator
import net.rebaat.mutaabid.presentation.viewmodel.WirdViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module


val presentationModule = module {
    single { DefaultNavigator("") }
    viewModel { WirdViewModel(get(), get()) }
}