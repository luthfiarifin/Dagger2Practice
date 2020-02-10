package com.laam.dagger2practice.util

import android.graphics.Rect
import android.view.View
import androidx.recyclerview.widget.RecyclerView


class VerticalSpacingItemDecoration(val verticalSpaceHeight: Int) : RecyclerView.ItemDecoration() {

    override fun getItemOffsets(
        outRect: Rect,
        view: View,
        parent: RecyclerView,
        state: RecyclerView.State
    ) {
        outRect.bottom = verticalSpaceHeight
    }
}