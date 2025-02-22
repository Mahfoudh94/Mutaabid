package net.rebaat.mutaabid.presentation

import androidx.lifecycle.SavedStateHandle
import net.rebaat.mutaabid.domain.DefaultNavigator
import net.rebaat.mutaabid.presentation.viewmodel.WirdViewModel
import org.koin.core.module.dsl.viewModelOf
import org.koin.dsl.module


val presentationModule = module {
    single { DefaultNavigator("") }
    single { SavedStateHandle() }
    viewModelOf(::WirdViewModel)
}