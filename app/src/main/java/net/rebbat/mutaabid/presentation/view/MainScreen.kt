package net.rebbat.mutaabid.presentation.view

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.expandVertically
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.shrinkVertically
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.AbsoluteAlignment
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.composables.icons.lucide.Lucide
import com.composables.icons.lucide.Plus
import net.rebbat.mutaabid._t
import net.rebbat.mutaabid.presentation.component.DayTabs
import net.rebbat.mutaabid.presentation.component.WirdCard
import net.rebbat.mutaabid.presentation.viewmodel.WirdViewModel
import net.rebbat.mutaabid.ui.theme.Gold
import org.koin.androidx.compose.koinViewModel

@Composable
fun MainScreen(
    modifier: Modifier = Modifier,
    wirdViewModel: WirdViewModel = koinViewModel(),
) {
    val islamicDate by wirdViewModel.selectedDate.collectAsState()
    val wirdItmams by wirdViewModel.wirdItmams.collectAsState()

    var isEditMode by remember { mutableStateOf(false) }

    Column(
        modifier = modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Top,
    ) {
        Button(
            modifier = Modifier
                .padding(20.dp)
                .align(AbsoluteAlignment.Right),
            colors = ButtonDefaults.buttonColors(containerColor = Gold),
            onClick = { isEditMode = !isEditMode }
        ) {
            Row(
                modifier = Modifier
                    .padding(0.dp, 8.dp)
                    .height(IntrinsicSize.Max),
                verticalAlignment = Alignment.CenterVertically,
            ) {
                Icon(imageVector = Lucide.Plus, "", modifier = Modifier.fillMaxHeight())
                Text(_t("ManageWirds.text") ?: "ManageWirds")
            }
        }

        DayTabs(islamicDate, wirdViewModel::onAction)

        if (wirdItmams.isEmpty())
            Text(
                _t("StarterMessage") ?: "Select a day",
                fontSize = MaterialTheme.typography.bodyLarge.fontSize.times(1.2f),
            )

        LazyColumn(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center,
            modifier = Modifier.weight(1f)
        ) {
            itemsIndexed(wirdItmams) { index, wirdItmam ->
                val isVisible = isEditMode || wirdItmam.wird.isAvailable == true

                AnimatedVisibility(
                    visible = isVisible,
                    enter = fadeIn() + expandVertically(),
                    exit = fadeOut() + shrinkVertically(),
                ) {
                    WirdCard(
                        wirdItmam,
                        isLast = index == wirdItmams.lastIndex,
                        isEditMode = isEditMode,
                        onAction = wirdViewModel::onAction
                    )
                }
            }
        }
    }
}