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

    private val fillPaint = Paint(Paint.ANTI_ALIAS_FLAG).apply {
        color = Color.WHITE
        style = Paint.Style.FILL
    }
    private val strokePaint = Paint(Paint.ANTI_ALIAS_FLAG).apply {
        color = Color.BLACK
        style = Paint.Style.STROKE
        strokeWidth = 3f
    }

    // Arrow cursor path (tip at origin, pointing up-left)
    private val arrowPath = Path()

    private val hideHandler = Handler(Looper.getMainLooper())
    private val hideRunnable = Runnable { alpha = 0f }

    companion object {
        private const val HIDE_DELAY_MS = 3_000L
        private const val CURSOR_SIZE = 48f // dp-ish at 160dpi; scales with density
    }

    init {
        // Start roughly centred; will be overridden before first draw
        cursorX = 540f
        cursorY = 540f
        isClickable = false
        isFocusable = false
        // Build the cursor arrow path once
        rebuildArrow()
    }

    private fun rebuildArrow() {
        val s = CURSOR_SIZE * resources.displayMetrics.density / 160f
        arrowPath.rewind()
        // Classic arrow: tip at (0,0), shaft down-right
        arrowPath.moveTo(0f, 0f)
        arrowPath.lineTo(0f, s * 1.4f)
        arrowPath.lineTo(s * 0.38f, s * 1.0f)
        arrowPath.lineTo(s * 0.72f, s * 1.7f)
        arrowPath.lineTo(s * 0.95f, s * 1.6f)
        arrowPath.lineTo(s * 0.62f, s * 0.88f)
        arrowPath.lineTo(s, s * 0.88f)
        arrowPath.close()
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
        canvas.drawPath(arrowPath, fillPaint)
        canvas.drawPath(arrowPath, strokePaint)
        canvas.restore()
    }

    override fun onDetachedFromWindow() {
        super.onDetachedFromWindow()
        hideHandler.removeCallbacks(hideRunnable)
    }
}
