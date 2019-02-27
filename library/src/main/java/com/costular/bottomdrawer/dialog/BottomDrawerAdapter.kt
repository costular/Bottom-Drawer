package com.costular.bottomdrawer.dialog

import android.graphics.PorterDuff
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.costular.bottomdrawer.R
import com.costular.bottomdrawer.model.DrawerItem
import com.costular.bottomdrawer.util.AttrHelper

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

    fun selectPosition(newPosition: Int) {
        notifyItemChanged(selectedPosition)
        selectedPosition = newPosition
        notifyItemChanged(selectedPosition)
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
                selectPosition(adapterPosition)
                clickListener.invoke(drawerItem, adapterPosition)
            }
        }
    }
}