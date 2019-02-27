package com.costular.bottomnavigationdrawer.model

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import java.lang.IllegalArgumentException

class DrawerItemBuilder : Builder<DrawerItem>() {

    @StringRes var stringRes: Int = -1
    @DrawableRes var drawableRes: Int = -1

    override fun build(): DrawerItem {
        if (stringRes == -1 || drawableRes == -1) {
            throw IllegalArgumentException("stringRes and drawableRes can't be empty")
        }
        return DrawerItem(drawableRes, stringRes)
    }

}