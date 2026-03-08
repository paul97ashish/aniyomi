package eu.kanade.tachiyomi.ui.main

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.Path
import android.os.Handler
import android.os.Looper
import android.view.View

/**
 * A transparent overlay [View] that draws a mouse-cursor arrow at [cursorX], [cursorY].
 * Auto-hides after [HIDE_DELAY_MS] of inactivity and reappears on the next D-pad event.
 */
class TvCursorView(context: Context) : View(context) {

    var cursorX: Float = 0f
        private set
    var cursorY: Float = 0f
        private set

    // Dark shadow drawn first (largest, offset), then dark outline, then white fill on top
    private val shadowPaint = Paint(Paint.ANTI_ALIAS_FLAG).apply {
        color = Color.BLACK
        style = Paint.Style.FILL
        alpha = 180
    }
    private val fillPaint = Paint(Paint.ANTI_ALIAS_FLAG).apply {
        color = Color.WHITE
        style = Paint.Style.FILL
    }
    private val strokePaint = Paint(Paint.ANTI_ALIAS_FLAG).apply {
        color = Color.BLACK
        style = Paint.Style.STROKE
        strokeWidth = 4f
        strokeJoin = Paint.Join.ROUND
        strokeCap = Paint.Cap.ROUND
    }

    // Arrow cursor path (tip at origin, pointing up-left)
    private val arrowPath = Path()
    private val shadowPath = Path()

    private val hideHandler = Handler(Looper.getMainLooper())
    private val hideRunnable = Runnable { alpha = 0f }

    companion object {
        private const val HIDE_DELAY_MS = 3_000L
        private const val CURSOR_SIZE = 36f // dp-ish at 160dpi; scales with density
        private const val SHADOW_OFFSET = 3f // px offset for drop shadow
    }

    init {
        cursorX = 540f
        cursorY = 540f
        isClickable = false
        isFocusable = false
        rebuildArrow()
    }

    private fun rebuildArrow() {
        val density = resources.displayMetrics.density
        val s = CURSOR_SIZE * density / 160f
        val shadow = SHADOW_OFFSET * density / 160f

        // Main arrow: tip at (0,0), shaft points down-right
        arrowPath.rewind()
        arrowPath.moveTo(0f, 0f)
        arrowPath.lineTo(0f, s * 1.4f)
        arrowPath.lineTo(s * 0.38f, s * 1.0f)
        arrowPath.lineTo(s * 0.72f, s * 1.7f)
        arrowPath.lineTo(s * 0.95f, s * 1.6f)
        arrowPath.lineTo(s * 0.62f, s * 0.88f)
        arrowPath.lineTo(s, s * 0.88f)
        arrowPath.close()

        // Shadow path is the same shape offset by (shadow, shadow)
        shadowPath.rewind()
        shadowPath.moveTo(shadow, shadow)
        shadowPath.lineTo(shadow, s * 1.4f + shadow)
        shadowPath.lineTo(s * 0.38f + shadow, s * 1.0f + shadow)
        shadowPath.lineTo(s * 0.72f + shadow, s * 1.7f + shadow)
        shadowPath.lineTo(s * 0.95f + shadow, s * 1.6f + shadow)
        shadowPath.lineTo(s * 0.62f + shadow, s * 0.88f + shadow)
        shadowPath.lineTo(s + shadow, s * 0.88f + shadow)
        shadowPath.close()
    }

    fun moveTo(x: Float, y: Float) {
        cursorX = x.coerceIn(0f, (width - 1).toFloat().coerceAtLeast(0f))
        cursorY = y.coerceIn(0f, (height - 1).toFloat().coerceAtLeast(0f))
        alpha = 1f
        invalidate()
        hideHandler.removeCallbacks(hideRunnable)
        hideHandler.postDelayed(hideRunnable, HIDE_DELAY_MS)
    }

    override fun onSizeChanged(w: Int, h: Int, oldw: Int, oldh: Int) {
        super.onSizeChanged(w, h, oldw, oldh)
        if (cursorX == 0f && cursorY == 0f) {
            cursorX = w / 2f
            cursorY = h / 2f
        }
    }

    override fun onDraw(canvas: Canvas) {
        canvas.save()
        canvas.translate(cursorX, cursorY)
        // 1. soft shadow
        canvas.drawPath(shadowPath, shadowPaint)
        // 2. white fill
        canvas.drawPath(arrowPath, fillPaint)
        // 3. black outline
        canvas.drawPath(arrowPath, strokePaint)
        canvas.restore()
    }

    override fun onDetachedFromWindow() {
        super.onDetachedFromWindow()
        hideHandler.removeCallbacks(hideRunnable)
    }
}
