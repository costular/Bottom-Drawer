package com.costular.bottomdrawer

import androidx.fragment.app.FragmentManager
import com.costular.bottomdrawer.dialog.BottomSheetDrawerDialog
import com.costular.bottomdrawer.model.DrawerItem

class BottomDrawer internal constructor(
    val theme: Int,
    val items: List<DrawerItem>,
    val clickListener: (drawerItem: DrawerItem, position: Int) -> Unit
) {

    var bottomSheetDrawerDialog: BottomSheetDrawerDialog? = null

    companion object {
        fun create(builder: BottomDrawerBuilder.() -> Unit): BottomDrawer = BottomDrawerBuilder().apply(builder).build()

        fun createAndShow(fragmentManager: FragmentManager, builder: BottomDrawerBuilder.() -> Unit): BottomDrawer {
            val instance = create(builder)
            instance.show(fragmentManager)
            return instance
        }
    }

    fun show(fragmentManager: FragmentManager) {
        if (bottomSheetDrawerDialog == null) {
            bottomSheetDrawerDialog = BottomSheetDrawerDialog.newInstance(items, theme, clickListener)
        }

        bottomSheetDrawerDialog?.show(fragmentManager, "bottom-drawer")
    }

    fun isOpen(fragmentManager: FragmentManager): Boolean = when {
        bottomSheetDrawerDialog != null && bottomSheetDrawerDialog?.isVisible ?: false -> true
        fragmentManager.findFragmentByTag("bottom-drawer")?.isVisible ?: false -> true
        else -> false
    }

    fun close() {
        bottomSheetDrawerDialog?.dismiss()
    }

}