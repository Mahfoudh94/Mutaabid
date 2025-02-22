package net.rebaat.mutaabid.presentation.view

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.Text
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import kotlinx.datetime.Clock
import kotlinx.datetime.LocalDate
import kotlinx.datetime.TimeZone
import kotlinx.datetime.format
import kotlinx.datetime.todayIn
import net.rebaat.mutaabid.presentation.action.WirdItmamAction
import net.rebaat.mutaabid.presentation.viewmodel.WirdViewModel

@Composable
fun MainScreen(
    wirdViewModel: WirdViewModel,
    modifier: Modifier = Modifier
) {
    val state = wirdViewModel.state
    val onAction = wirdViewModel::onAction

    Column(
        modifier = modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Top,
    ) {
        Text(
            "Just a page",
            fontSize = 30.sp,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.padding(0.dp, 8.dp),
        )
        LazyColumn(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center,
            modifier = Modifier.weight(1f, fill = true)
        ) {
            when (state.isLoading) {
                true -> {
                    item {
                        Box(modifier = Modifier.size(100.dp)) {
                            Text("Loading...")
                        }
                    }
                }

                else -> {
                    item {
                        Button(
                            onClick = {
                                onAction(
                                    WirdItmamAction.SelectDate(
                                        Clock.System.todayIn(
                                            TimeZone.currentSystemDefault()
                                        )
                                    )
                                )
                            }
                        ) {
                            Text(
                                Clock.System.todayIn(TimeZone.currentSystemDefault())
                                    .format(LocalDate.Formats.ISO),
                                modifier = Modifier.padding(0.dp, 8.dp),
                                fontSize = 20.sp,
                                fontWeight = FontWeight.Medium,
                            )
                        }
                    }
                }
            }
            items(state.wirdItmams) { wirdItmam ->
                Button(
                    onClick = { onAction(WirdItmamAction.ToggleItmamWird(wirdItmam)) },
                    colors = when (wirdItmam.itmam?.done) {
                        true -> ButtonColors(
                            contentColor = lightColorScheme().background,
                            containerColor = lightColorScheme().primary,
                            disabledContentColor = Color.Gray,
                            disabledContainerColor = Color.Gray,
                        )

                        else -> ButtonColors(
                            contentColor = lightColorScheme().primary,
                            containerColor = lightColorScheme().background,
                            disabledContentColor = Color.Gray,
                            disabledContainerColor = Color.Gray,
                        )
                    }
                ) {
                    Text(wirdItmam.wird.name)
                }
            }
        }
    }
}