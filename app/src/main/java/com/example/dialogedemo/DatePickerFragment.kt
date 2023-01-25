package com.example.dialogedemo

import android.app.DatePickerDialog
import android.app.Dialog
import android.content.Context
import android.os.Bundle
import androidx.fragment.app.DialogFragment
import java.util.Calendar

class DatePickerFragment : DialogFragment() {

    private var listener: DatePickerDialog.OnDateSetListener? = null

    override fun onAttach(context: Context) {
        super.onAttach(context)
        if (context is DatePickerDialog.OnDateSetListener)
            listener = context
    }

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val calendar = Calendar.getInstance()
        val year = calendar[Calendar.YEAR]
        val month = calendar[Calendar.MONTH]
        val day = calendar[Calendar.DAY_OF_MONTH]

        return DatePickerDialog(requireActivity(), listener, year, month, day)
    }

    companion object {
        val TAG = DatePickerFragment::class.java.simpleName
    }
}