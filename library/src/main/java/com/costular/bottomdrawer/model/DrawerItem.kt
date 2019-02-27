package com.costular.bottomdrawer.model

import android.os.Parcel
import android.os.Parcelable
import androidx.annotation.DrawableRes
import androidx.annotation.StringRes

data class DrawerItem(
    @DrawableRes val drawableRes: Int,
    @StringRes val stringRes: Int
): Parcelable {

    constructor(parcel: Parcel) : this(
        parcel.readInt(),
        parcel.readInt()
    )

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeInt(drawableRes)
        parcel.writeInt(stringRes)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<DrawerItem> {
        override fun createFromParcel(parcel: Parcel): DrawerItem {
            return DrawerItem(parcel)
        }

        override fun newArray(size: Int): Array<DrawerItem?> {
            return arrayOfNulls(size)
        }
    }
}