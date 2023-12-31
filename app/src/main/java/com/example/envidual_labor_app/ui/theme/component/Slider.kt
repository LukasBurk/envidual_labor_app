package com.example.envidual_labor_app.ui.theme.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.defaultMinSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Slider
import androidx.compose.material3.SliderColors
import androidx.compose.material3.SliderDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.example.envidual_labor_app.R

@Composable
fun SliderWithLabel(
    value: Float,
    valueRange: ClosedFloatingPointRange<Float>,
    finiteEnd: Boolean,
    labelMinWidth: Dp = 24.dp,
) {

    Column() {

        BoxWithConstraints(
            modifier = Modifier
                .fillMaxWidth()
        ) {
            val offset = getSliderOffset(
                value = value,
                valueRange = valueRange,
                boxWidth = maxWidth,
                labelWidth = labelMinWidth + 8.dp,

            )
            val endValueText =
                if (!finiteEnd && value >= valueRange.endInclusive) "${
                    value.toInt()
                }+" else value.toInt().toString()

            if (value > valueRange.start) {
                SliderLabel(
                    label = endValueText, minWidth = labelMinWidth, modifier = Modifier
                        .padding(start = offset)
                )
            }
        }
        Slider(
            value = value, onValueChange = {
            },
            valueRange = valueRange,
            colors = SliderDefaults.colors(
                thumbColor = colorResource(R.color.green_500),
                activeTrackColor = colorResource(R.color.green_500),
                inactiveTrackColor = colorResource(R.color.orange_500)),
            modifier = Modifier.fillMaxWidth()
        )
    }
}

@Composable
fun SliderLabel(label: String, minWidth: Dp, modifier: Modifier = Modifier) {
    Text(
        label,
        textAlign = TextAlign.Center,
        color = Color.White,
        modifier = modifier
            .background(
                color = colorResource(R.color.green_500),
                shape = RoundedCornerShape(4.dp)
            )
            .padding(4.dp)
            .defaultMinSize(minWidth = minWidth)
    )
}

private fun getSliderOffset(
    value: Float,
    valueRange: ClosedFloatingPointRange<Float>,
    boxWidth: Dp,
    labelWidth: Dp
): Dp {

    val coerced = value.coerceIn(valueRange.start, valueRange.endInclusive)
    val positionFraction = calcFraction(valueRange.start, valueRange.endInclusive, coerced)

    return (boxWidth - labelWidth) * positionFraction
}
private fun calcFraction(a: Float, b: Float, pos: Float) =
    (if (b - a == 0f) 0f else (pos - a) / (b - a)).coerceIn(0f, 1f)