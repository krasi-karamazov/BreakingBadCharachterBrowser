package kpk.dev.feature_character_list.presentation.characterlist

import android.app.Dialog
import android.os.Bundle
import android.util.SparseBooleanArray
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.DialogFragment
import kpk.dev.feature_character_list.R

class FilterDialogFragment(val listener: (SparseBooleanArray) -> Unit) : DialogFragment() {
    companion object {
        const val TAG = "FilterDialogFragment"

        const val EXTRA_SELECTIONS = "selections"

        fun getInstance(
            args: Bundle?,
            listener: (SparseBooleanArray) -> Unit
        ): FilterDialogFragment {
            val fragment = FilterDialogFragment(listener)
            fragment.arguments = args
            return fragment
        }
    }

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val builder = AlertDialog.Builder(context!!)

        val checkedItems = arguments?.getBooleanArray(EXTRA_SELECTIONS) ?: booleanArrayOf(
            false,
            false,
            false,
            false,
            false
        )
        val options = resources.getStringArray(R.array.seasons)

        builder.setTitle(getString(R.string.filter_title))
        builder.setMultiChoiceItems(options, checkedItems, null)
        builder.setPositiveButton(getString(R.string.ok)) { _, _ ->
            listener((dialog as AlertDialog).listView.checkedItemPositions)
        }

        builder.setNeutralButton("Clear filter") { _, _ ->
            (dialog as AlertDialog).listView.clearChoices()
            listener((dialog as AlertDialog).listView.checkedItemPositions)
        }

        builder.setNegativeButton(getString(R.string.cancel)) { _, _ ->
            dismiss()
        }
        return builder.show()
    }


}