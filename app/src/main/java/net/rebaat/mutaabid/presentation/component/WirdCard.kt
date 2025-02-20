package net.rebaat.mutaabid.presentation.component

import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import net.rebaat.mutaabid.R
import net.rebaat.mutaabid.data.model.Wird

@Composable
fun WirdCard(
    wird: Wird,
    isEditMode: Boolean = false
) {
    val scale = animateFloatAsState(if (isEditMode) 1f else 0f)
    var wirdState: MutableState<Wird> = remember { mutableStateOf(wird) }

    return Button(
        modifier = Modifier.background(color = Color.White)
            .border(
                width = 4.dp,
                color = lightColorScheme().primary,
            ),
        onClick = { /* TODO */},
    ) {
        Row(modifier = Modifier.padding(8.dp)) {
            Image(
                painter = painterResource(R.drawable.praying),
                contentDescription = "Wird Image",
                modifier = Modifier.fillMaxHeight()
            )
            Text(wird.name.orEmpty())

        }
    }
}