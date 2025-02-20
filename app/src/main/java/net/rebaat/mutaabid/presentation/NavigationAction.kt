package net.rebaat.mutaabid.presentation

sealed interface NavigationAction {
    data class NavigateTo(val route: String) : NavigationAction
    data object NavigateBack : NavigationAction
}