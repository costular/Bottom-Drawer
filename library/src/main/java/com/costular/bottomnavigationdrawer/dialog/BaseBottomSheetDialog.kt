package com.costular.bottomnavigationdrawer.dialog

import android.app.Dialog
import android.os.Bundle
import com.costular.bottomnavigationdrawer.R
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.google.android.material.bottomsheet.BottomSheetDialogFragment

abstract class BaseBottomSheetDialog : BottomSheetDialogFragment() {

    abstract override fun getTheme(): Int

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog = BottomSheetDialog(requireContext(), theme)

}