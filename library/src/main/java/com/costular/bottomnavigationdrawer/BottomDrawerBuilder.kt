package com.costular.bottomnavigationdrawer

import com.costular.bottomnavigationdrawer.model.Builder
import com.costular.bottomnavigationdrawer.model.DrawerItem
import com.costular.bottomnavigationdrawer.model.DrawerItemBuilder

class BottomDrawerBuilder : Builder<BottomDrawer>() {

    var items: MutableList<DrawerItem> = mutableListOf()
    var theme: Int = R.style.BottomNavigationDrawer_Light
    var clickListener: (drawerItem: DrawerItem, position: Int) -> Unit = { _, _ -> }

    fun drawerItem(block: DrawerItemBuilder.() -> Unit) = items.add(DrawerItemBuilder().apply(block).build())

    override fun build(): BottomDrawer {
        return BottomDrawer(theme, items, clickListener)
    }

}