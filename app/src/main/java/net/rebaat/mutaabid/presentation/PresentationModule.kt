package net.rebaat.mutaabid.presentation

import androidx.lifecycle.SavedStateHandle
import net.rebaat.mutaabid.presentation.viewmodel.WirdViewModel
import org.koin.core.module.dsl.viewModelOf
import org.koin.dsl.module


val presentationModule = module {
    single { SavedStateHandle() }
    viewModelOf(::WirdViewModel)
}