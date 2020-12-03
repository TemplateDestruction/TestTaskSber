package com.test.sber.presentation.view.custom

import android.annotation.SuppressLint
import android.content.Context
import android.util.AttributeSet
import android.view.MotionEvent
import android.view.animation.DecelerateInterpolator
import android.widget.Scroller
import androidx.viewpager.widget.ViewPager

class SmoothSwipeableViewPager @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null
): ViewPager(context, attrs) {

    init {
        setSmoothScroller()
    }

    //down one is added for smooth scrolling
    private fun setSmoothScroller() {
        try {
            val viewpager = ViewPager::class.java
            val scroller = viewpager.getDeclaredField("mScroller")
            scroller.isAccessible = true
            scroller.set(this, SmoothScroller(context))
        } catch (e: Exception) {
        }

    }

    inner class SmoothScroller(context: Context) : Scroller(context, DecelerateInterpolator()) {

        override fun startScroll(startX: Int, startY: Int, dx: Int, dy: Int, duration: Int) {
            super.startScroll(startX, startY, dx, dy, 350 /*1 secs*/)
        }
    }
}