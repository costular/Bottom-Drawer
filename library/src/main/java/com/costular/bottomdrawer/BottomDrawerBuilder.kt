package com.costular.bottomdrawer

import com.costular.bottomdrawer.model.Builder
import com.costular.bottomdrawer.model.DrawerItem
import com.costular.bottomdrawer.model.DrawerItemBuilder

class BottomDrawerBuilder : Builder<BottomDrawer>() {

    var items: MutableList<DrawerItem> = mutableListOf()
    var theme: Int = R.style.BottomNavigationDrawer_Light
    var selectedPosition: Int = 0
    var clickListener: (drawerItem: DrawerItem, position: Int) -> Boolean = { _, _ -> true }

    fun drawerItem(block: DrawerItemBuilder.() -> Unit) = items.add(DrawerItemBuilder().apply(block).build())

    override fun build(): BottomDrawer {
        return BottomDrawer(theme, items, selectedPosition, clickListener)
    }

}