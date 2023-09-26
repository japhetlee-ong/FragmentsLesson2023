package ph.edu.auf.fragmentslesson.fragments.dialogs

import android.app.Dialog
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.DialogFragment
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import ph.edu.auf.fragmentslesson.R

class BasicDialogFragment : DialogFragment() {

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog =
        MaterialAlertDialogBuilder(requireContext())
            .setTitle("Dialog fragment")
            .setMessage("Hello there")
            .setIcon(R.drawable.ic_email)
            .setPositiveButton("Confirm") {dialog, which ->
                Log.d(TAG,"Confirm clicked")
            }
            .setNegativeButton("Cancel") {dialog, which ->
                Log.d(TAG,"Cancel clicked")
            }
            .setNeutralButton("Neutral") {dialog, which ->
                Log.d(TAG,"Neutral clicked")
            }
            .create()

    companion object {
        const val TAG = "BasicDialog"
    }
}