package net.rebaat.mutaabid.presentation.view

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import net.rebaat.mutaabid.presentation.component.WirdCard
import net.rebaat.mutaabid.presentation.viewmodel.WirdViewModel

@Composable
fun MainScreen(
    wirdViewModel: WirdViewModel,
    modifier: Modifier = Modifier
) {
    val wirdState by wirdViewModel.wirdState.collectAsState()
    Box(
        contentAlignment = Alignment.TopCenter,
        modifier = modifier.fillMaxSize()
    ) {
        LazyColumn {
            items(wirdState.wirds) { wird ->
                WirdCard(wird = wird)
            }
        }
    }
}