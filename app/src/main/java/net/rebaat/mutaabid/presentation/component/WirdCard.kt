package net.rebaat.mutaabid.presentation.component

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.expandHorizontally
import androidx.compose.animation.shrinkHorizontally
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.remember
import androidx.compose.ui.AbsoluteAlignment
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalLayoutDirection
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.LayoutDirection
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.unit.times
import com.composables.icons.lucide.CircleMinus
import com.composables.icons.lucide.CirclePlus
import com.composables.icons.lucide.Lucide
import net.rebaat.mutaabid.R
import net.rebaat.mutaabid._t
import net.rebaat.mutaabid.data.model.WirdItmam
import net.rebaat.mutaabid.presentation.action.WirdItmamAction
import net.rebaat.mutaabid.ui.theme.MutedPrimary

@Composable
fun WirdCard(
    wirdItmam: WirdItmam,
    isLast: Boolean = false,
    isEditMode: Boolean,
    onAction: (WirdItmamAction) -> Unit = {}
) {
    val isVisible = remember { mutableStateOf(wirdItmam.wird.isAvailable) }
    val canvasItemsColor = MaterialTheme.colorScheme.primary

    return Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier.height(IntrinsicSize.Max)
    ) {
        Button(
            onClick = { onAction(WirdItmamAction.ToggleItmamWird(wirdItmam)) },
            colors = when (wirdItmam.itmam?.done) {
                true -> ButtonColors(
                    contentColor = MaterialTheme.colorScheme.background,
                    containerColor = MaterialTheme.colorScheme.primary,
                    disabledContentColor = Color.Gray,
                    disabledContainerColor = Color.Gray,
                )

                else -> ButtonColors(
                    contentColor = MaterialTheme.colorScheme.primary,
                    containerColor = MutedPrimary,
                    disabledContentColor = Color.Gray,
                    disabledContainerColor = Color.Gray,
                )
            },
            modifier = Modifier
                .padding(16.dp, 8.dp)
                .weight(1f),
            shape = RoundedCornerShape(32.dp),
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(10.dp),
            ) {
                Box(
                    modifier = Modifier
                        .width(70.dp)
                        .padding(vertical = 10.dp)
                        .aspectRatio(1f)
                ) {
                    if (wirdItmam.wird.icon != null) Icon(
                        painter = painterResource(id = wirdItmam.wird.icon),
                        contentDescription = "",
                        modifier = Modifier.fillMaxSize(),
                    )
                }
                Column(
                    modifier = Modifier.weight(1f),
                    horizontalAlignment = AbsoluteAlignment.Right,
                ) {
                    CompositionLocalProvider(LocalLayoutDirection provides LayoutDirection.Rtl) {
                        Text(
                            _t("${wirdItmam.wird.name}.title") ?: "<--->",
                            modifier = Modifier.fillMaxWidth(),
                            textAlign = TextAlign.Right,
                            fontWeight = FontWeight.Bold,
                            fontSize = 16.sp
                        )
                        Text(
                            _t("${wirdItmam.wird.name}.desc") ?: "<--->",
                            modifier = Modifier.fillMaxWidth(),
                            textAlign = TextAlign.Right,
                            fontSize = 12.sp
                        )
                    }
                }
                AnimatedVisibility(
                    visible = isEditMode,
                    enter = expandHorizontally(),
                    exit = shrinkHorizontally(),
                ) {
                    IconButton(
                        onClick = {
                            onAction(WirdItmamAction.ToggleWirdVisibility(wirdItmam.wird))
                        },
                    ) {
                        when (wirdItmam.wird.isAvailable) {
                            true -> Icon(
                                imageVector = Lucide.CircleMinus,
                                contentDescription = "Hide",
                                tint = Color.Red,
                            )

                            else -> Icon(
                                imageVector = Lucide.CirclePlus,
                                contentDescription = "Show",
                                tint = Color.Black,
                            )
                        }
                    }
                }
            }
        }
        Box(
            modifier = Modifier
                .width(30.dp)
                .fillMaxHeight()
                .padding(0.dp, 8.dp)
        ) {
            Canvas(modifier = Modifier.fillMaxHeight()) {
                val centerX = size.width / 2
                drawCircle(
                    color = canvasItemsColor,
                    radius = 18f,
                    center = Offset(centerX, 10.dp.toPx())
                )
                if (wirdItmam.itmam?.done == true) drawCircle(
                    color = canvasItemsColor,
                    radius = 26f,
                    center = Offset(centerX, 10.dp.toPx()),
                    style = Stroke(width = 5f)
                )
                if (!isLast) drawLine(
                    color = canvasItemsColor,
                    start = Offset(centerX, 32.dp.toPx()),
                    end = Offset(centerX, size.height),
                    strokeWidth = 6f
                )
            }
        }
    }
}