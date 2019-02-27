package com.costular.bottomnavigationdrawer.util

import android.content.Context
import androidx.core.content.res.TypedArrayUtils.getResourceId
import androidx.core.content.ContextCompat
import androidx.annotation.ColorInt
import android.content.res.TypedArray
import androidx.annotation.AttrRes



object AttrHelper {

    @ColorInt
    fun getAttributeColor(context: Context, @AttrRes colorAttribute: Int): Int {
        val attrs = intArrayOf(colorAttribute)
        val ta = context.obtainStyledAttributes(attrs)

        val color = ContextCompat.getColor(context,
                ta.getResourceId(0, -1))
        ta.recycle()

        return color
    }

}