package com.costular.bottomdrawer.dialog

import android.os.Bundle
import android.os.Handler
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.StyleRes
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.costular.bottomdrawer.R
import com.costular.bottomdrawer.model.DrawerItem

class BottomSheetDrawerDialog : BaseBottomSheetDialog() {

    interface ClickCallback {
        fun onClick(drawerItem: DrawerItem, position: Int)
    }

    private val handler by lazy { Handler() }

    private var theme: Int = -1
    lateinit var bottomDrawerAdapter: BottomDrawerAdapter

    val recycler: RecyclerView by lazy {
        view!!.findViewById<RecyclerView>(R.id.recyclerDrawer)
    }

    var clickListener: ClickCallback? = null
    lateinit var items: Array<DrawerItem>

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_bottom_drawer, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        requireNotNull(arguments!!)
        setUp(arguments!!)
    }

    private fun setUp(bundle: Bundle) {
        items = bundle.getParcelableArray(PARAM_ITEMS) as Array<DrawerItem>
        val position = bundle.getInt("PARAM_POSITION", -1)

        bottomDrawerAdapter = BottomDrawerAdapter(items) { drawerItem, position ->
            clickListener?.onClick(drawerItem, position)

            // Dismiss with delay
            handler.postDelayed({
                dismiss()
            }, 300)
        }
        setUpRecycler()

        if (position > -1) {
            bottomDrawerAdapter.selectPosition(position)
        }
    }

    private fun setUpRecycler() {
        with(recycler) {
            layoutManager = LinearLayoutManager(activity, RecyclerView.VERTICAL, false)
            itemAnimator = DefaultItemAnimator()
            adapter = bottomDrawerAdapter
        }
    }

    override fun onSaveInstanceState(outState: Bundle) {
        outState.putInt("PARAM_POSITION", bottomDrawerAdapter.selectedPosition)
        super.onSaveInstanceState(outState)
    }

    fun close() {
        dismiss()
    }

    override fun getTheme(): Int = theme

    companion object {
        fun newInstance(
            items: List<DrawerItem>,
            @StyleRes dialogTheme: Int,
            clickCallback: (drawerItem: DrawerItem, position: Int) -> Unit = { _, _ -> }
        ): BottomSheetDrawerDialog {
            return BottomSheetDrawerDialog().apply {
                arguments = Bundle().apply {
                    putParcelableArray(PARAM_ITEMS, items.toTypedArray())
                    theme = dialogTheme
                    clickListener = object : ClickCallback {
                        override fun onClick(drawerItem: DrawerItem, position: Int) {
                            clickCallback.invoke(drawerItem, position)
                        }
                    }
                }
            }
        }

        const val PARAM_ITEMS = "items"
    }

}