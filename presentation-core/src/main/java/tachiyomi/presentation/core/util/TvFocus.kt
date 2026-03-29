package tachiyomi.presentation.core.util

import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.compositionLocalOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.composed
import androidx.compose.ui.draw.drawWithContent
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.geometry.CornerRadius
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

/**
 * CompositionLocal that screens read to gate TV-only behaviour.
 * Provided as `true` in MainActivity when running on a Leanback device.
 */
val LocalIsTvUi = compositionLocalOf { false }

/**
 * Draws a rounded highlight border and scales to 1.05f when the element has focus.
 * Only applies the visual effect when [LocalIsTvUi] is `true`.
 *
 * Uses [composed] so Compose state can be read inside the modifier.
 */
fun Modifier.tvFocusHighlight(
    shape: Shape = RoundedCornerShape(8.dp),
    borderWidth: Dp = 3.dp,
    color: Color = Color.White,
): Modifier = composed {
    val isTvUi = LocalIsTvUi.current
    if (!isTvUi) return@composed this

    var isFocused by remember { mutableStateOf(false) }

    this
        .onFocusChanged { isFocused = it.isFocused }
        .graphicsLayer {
            scaleX = if (isFocused) 1.05f else 1f
            scaleY = if (isFocused) 1.05f else 1f
        }
        .drawWithContent {
            drawContent()
            if (isFocused) {
                val strokeWidthPx = borderWidth.toPx()
                val cornerRadius = 8.dp.toPx()
                drawRoundRect(
                    color = color,
                    topLeft = Offset(strokeWidthPx / 2f, strokeWidthPx / 2f),
                    size = Size(
                        width = size.width - strokeWidthPx,
                        height = size.height - strokeWidthPx,
                    ),
                    cornerRadius = CornerRadius(cornerRadius, cornerRadius),
                    style = Stroke(width = strokeWidthPx),
                )
            }
        }
}
