package net.rebbat.mutaabid.presentation

import net.rebbat.mutaabid.presentation.viewmodel.WirdViewModel
import org.koin.core.module.dsl.viewModelOf
import org.koin.dsl.module


val presentationModule = module {
    viewModelOf(::WirdViewModel)
}