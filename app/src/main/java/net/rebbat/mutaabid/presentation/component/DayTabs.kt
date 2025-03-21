package net.rebbat.mutaabid.presentation.component

import android.icu.util.IslamicCalendar
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import net.rebbat.mutaabid.DAY
import net.rebbat.mutaabid.DAYS_IN_MONTH
import net.rebbat.mutaabid.DAY_OF_WEEK
import net.rebbat.mutaabid.MONTH
import net.rebbat.mutaabid.YEAR
import net.rebbat.mutaabid._t
import net.rebbat.mutaabid.presentation.action.WirdItmamAction
import net.rebbat.mutaabid.presentation.viewmodel.WirdViewModel

@Composable
fun DayTabs(
    islamicDate: IslamicCalendar,
    onAction: (WirdItmamAction) -> Unit
) {
    val lazyListState = rememberLazyListState()

    val itemWidth = 48.dp
    val screenWidth = LocalConfiguration.current.screenWidthDp.dp
    val horizontalPadding = (screenWidth - itemWidth) / 2

    val currentLocalDensity = LocalDensity.current
    LaunchedEffect(Unit) {
        with(currentLocalDensity) {
            val itemOffset = itemWidth.div(2).minus(8.dp).toPx().toInt()
            lazyListState.scrollToItem(islamicDate.DAY - 1, itemOffset)
        }
    }

    return Column(horizontalAlignment = Alignment.CenterHorizontally) {
        MonthYearLabel(islamicDate)
        LazyRow(
            state = lazyListState,
            contentPadding = PaddingValues(horizontal = horizontalPadding),
            horizontalArrangement = Arrangement.spacedBy(8.dp),
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.fillMaxWidth(),
        ) {
            if (IslamicCalendar().DAY - 3 <= 0) item { PrevMonthButton(islamicDate, onAction) }

            items(29 + islamicDate.get(IslamicCalendar.IS_LEAP_MONTH)) { index ->
                val dayNumber = index + 1
                val dayName =
                    _t("DayName.${(islamicDate.DAY_OF_WEEK + index - islamicDate.DAY + 35) % 7 + 1}")
                val isSelected = dayNumber == islamicDate.DAY
                val isEnabled = when {
                    islamicDate.YEAR < IslamicCalendar().YEAR -> true
                    islamicDate.YEAR > IslamicCalendar().YEAR -> false
                    else -> when {
                        islamicDate.MONTH < IslamicCalendar().MONTH -> true
                        islamicDate.MONTH > IslamicCalendar().MONTH -> false
                        else -> dayNumber <= IslamicCalendar().DAY + 2
                    }
                }

                Box(
                    modifier = Modifier
                        .width(itemWidth + 32.dp)
                        .padding(8.dp)
                        .aspectRatio(1.2f)
                        .clip(RoundedCornerShape(16.dp))
                        .clickable(
                            onClick = {
                                if (islamicDate.DAY == dayNumber) return@clickable;
                                val date = islamicDate.clone() as IslamicCalendar
                                date.set(IslamicCalendar.DAY_OF_MONTH, dayNumber)
                                onAction(WirdItmamAction.SelectDate(date))
                            }, enabled = isEnabled
                        ), contentAlignment = Alignment.Center
                ) {
                    Text(
                        text = "${dayName}\n${dayNumber.toString()}",
                        textAlign = TextAlign.Center,
                        fontWeight = when {
                            isSelected -> FontWeight.Bold
                            isEnabled -> FontWeight.SemiBold
                            else -> FontWeight.Normal
                        },
                        color = if (isSelected) MaterialTheme.colorScheme.primary
                        else Color.Gray.copy(alpha = if (isEnabled) .6f else .3f)
                    )
                }
            }

            if (IslamicCalendar().DAY + 3 > 30) item { NextMonthButton(islamicDate, onAction) }
        }
    }
}

@Composable
fun MonthYearLabel(islamicDate: IslamicCalendar) {
    val monthNumber = islamicDate.MONTH
    val yearNumber = islamicDate.YEAR
    Text(
        (_t("MonthName.$monthNumber") ?: "شهر مجهول") + " " + yearNumber,
        fontWeight = FontWeight.Bold,
        fontSize = MaterialTheme.typography.titleLarge.fontSize,
        color = MaterialTheme.colorScheme.primary
    )
}

@Composable
private fun NextMonthButton(
    islamicDate: IslamicCalendar,
    onAction: (WirdItmamAction) -> Unit
) {
    return Button(
        shape = RoundedCornerShape(8.dp), colors = ButtonDefaults.textButtonColors(), onClick = {
            islamicDate.add(IslamicCalendar.MONTH, 1)
            islamicDate.set(IslamicCalendar.DAY_OF_MONTH, 1)
            onAction(WirdItmamAction.SelectDate(islamicDate))
        }) {
        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            Text("<", fontSize = 20.sp, fontWeight = FontWeight.Normal)
            Text(
                _t("MonthName.${(islamicDate.MONTH + 1) % 12}") ?: "التالي",
                fontSize = 14.sp,
                fontWeight = FontWeight.Medium
            )
        }
    }
}

@Composable
private fun PrevMonthButton(
    islamicDate: IslamicCalendar,
    onAction: (WirdItmamAction) -> Unit
) {
    return Button(
        shape = RoundedCornerShape(8.dp), colors = ButtonDefaults.textButtonColors(), onClick = {
            islamicDate.add(IslamicCalendar.MONTH, -1)
            islamicDate.set(IslamicCalendar.DAY_OF_MONTH, islamicDate.DAYS_IN_MONTH)
            onAction(WirdItmamAction.SelectDate(islamicDate))
        }) {
        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            Text("<", fontSize = 20.sp, fontWeight = FontWeight.Normal)
            Text(
                _t("MonthName.${(islamicDate.MONTH + 11) % 12}") ?: "السابق",
                fontSize = 14.sp,
                fontWeight = FontWeight.Medium
            )
        }
    }
}
