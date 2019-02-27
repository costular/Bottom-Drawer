package com.costular.bottomnavigationdrawer.dialog

import android.content.Context
import android.graphics.PorterDuff
import android.graphics.Typeface
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.costular.bottomnavigationdrawer.R
import com.costular.bottomnavigationdrawer.model.DrawerItem
import com.costular.bottomnavigationdrawer.util.AttrHelper
import java.lang.ref.WeakReference

class BottomDrawerAdapter(
    val items: Array<DrawerItem>,
    val clickListener: (drawerItem: DrawerItem, position: Int) -> Unit
) : RecyclerView.Adapter<BottomDrawerAdapter.BottomDrawerViewHolder>() {

    var selectedPosition = 0

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BottomDrawerViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_drawer, parent, false)
        return BottomDrawerViewHolder(view)
    }

    override fun getItemCount(): Int = items.size

    override fun onBindViewHolder(holder: BottomDrawerViewHolder, position: Int) {
        holder.bind(items[position])
    }

    inner class BottomDrawerViewHolder(view: View) : RecyclerView.ViewHolder(view) {

        val itemTitle by lazy {
            itemView.findViewById<TextView>(R.id.btdrItemText)
        }
        val itemIcon by lazy {
            itemView.findViewById<ImageView>(R.id.btdrItemIcon)
        }

        fun bind(drawerItem: DrawerItem) {
            itemTitle.setText(drawerItem.stringRes)
            itemIcon.setImageResource(drawerItem.drawableRes)

            if(selectedPosition == adapterPosition) {
                itemView.isActivated = true

                val selectedColor = AttrHelper.getAttributeColor(itemView.context, R.attr.colorSelected)
                itemTitle.setTextColor(selectedColor)
                itemIcon.setColorFilter(selectedColor, PorterDuff.Mode.SRC_ATOP)
            } else {
                itemView.isActivated = false

                val inactiveColor = AttrHelper.getAttributeColor(itemView.context, android.R.attr.textColorPrimary)
                val inactiveColorIcon = AttrHelper.getAttributeColor(itemView.context, R.attr.colorIcon)
                itemTitle.setTextColor(inactiveColor)
                itemIcon.setColorFilter(inactiveColorIcon, PorterDuff.Mode.SRC_ATOP)
            }

            itemView.setOnClickListener {
                notifyItemChanged(selectedPosition)
                selectedPosition = adapterPosition
                notifyItemChanged(selectedPosition)
                clickListener.invoke(drawerItem, adapterPosition)
            }
        }
    }
}