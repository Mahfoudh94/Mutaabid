package net.rebaat.mutaabid.domain

import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.receiveAsFlow
import net.rebaat.mutaabid.presentation.NavigationAction

interface Navigator {
    val startRoute: String
    val navigationAction: Flow<NavigationAction>

    suspend fun navigateTo(route: String)
    suspend fun navigateBack()
}

class DefaultNavigator(
    override val startRoute: String
): Navigator {
    private val _navigationAction = Channel<NavigationAction>()
    override val navigationAction = _navigationAction.receiveAsFlow()

    override suspend fun navigateTo(route: String) {
        _navigationAction.send(NavigationAction.NavigateTo(route))
    }

    override suspend fun navigateBack() {
        _navigationAction.send(NavigationAction.NavigateBack)
    }
}