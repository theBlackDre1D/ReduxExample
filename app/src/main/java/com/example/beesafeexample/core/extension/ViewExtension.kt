package com.example.beesafeexample.core.extension

import android.view.View
import android.view.ViewGroup

fun View.visibleOrGone(visible: Boolean, animated: Boolean = false) {
    setVisibility(this, visible, true, animated)
}

fun View.visibleOrInvisible(visible: Boolean, animated: Boolean = false) {
    setVisibility(this, visible, false, animated)
}

fun View.setLeftMargin(margin: Int) {
    if (this.layoutParams is ViewGroup.MarginLayoutParams) {
        val p = this.layoutParams as ViewGroup.MarginLayoutParams
        p.leftMargin = margin
        this.requestLayout()
    }
}

fun View.setRightMargin(margin: Int) {
    if (this.layoutParams is ViewGroup.MarginLayoutParams) {
        val p = this.layoutParams as ViewGroup.MarginLayoutParams
        p.rightMargin = margin
        this.requestLayout()
    }
}

fun View.setTopMargin(margin: Int) {
    if (this.layoutParams is ViewGroup.MarginLayoutParams) {
        val p = this.layoutParams as ViewGroup.MarginLayoutParams
        p.topMargin = margin
        this.requestLayout()
    }
}

fun View.setBottomMargin(margin: Int) {
    if (this.layoutParams is ViewGroup.MarginLayoutParams) {
        val p = this.layoutParams as ViewGroup.MarginLayoutParams
        p.bottomMargin = margin
        this.requestLayout()
    }
}

fun View.setMargins(l: Int, t: Int, r: Int, b: Int) {
    if (this.layoutParams is ViewGroup.MarginLayoutParams) {
        val p = this.layoutParams as ViewGroup.MarginLayoutParams
        p.setMargins(l, t, r, b)
        this.requestLayout()
    }
}

private fun setVisibility(view: View, visible: Boolean, gone: Boolean = false, animated: Boolean = false){
    val isVisible = view.visibility == View.VISIBLE

    val show = {
        view.visibility = View.VISIBLE
    }

    val hide = {
        if (gone) view.visibility = View.GONE
        else view.visibility = View.INVISIBLE
    }

    when {
        animated && visible && !isVisible -> {
            view.alpha = 0f
            show()
            view.animate().alpha(1f)
        }
        animated && !visible && isVisible -> {
            view.animate().alpha(0f).withEndAction {
                hide()
                view.alpha = 1f
            }
        }
        else -> {
            if(visible) show() else hide()
        }
    }
}