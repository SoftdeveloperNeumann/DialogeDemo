package com.example.dialogedemo

import android.app.Dialog
import android.app.TimePickerDialog
import android.content.Context
import android.os.Bundle
import androidx.fragment.app.DialogFragment
import java.util.Calendar

class TimePickerFragment : DialogFragment(){

    private  var listener : TimePickerDialog.OnTimeSetListener? = null
    lateinit var timePickerDialog: TimePickerDialog

    override fun onAttach(context: Context) {
        super.onAttach(context)
        if(context is TimePickerDialog.OnTimeSetListener)
            listener = context
    }

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {

        val calendar = Calendar.getInstance()
        val hour = calendar[Calendar.HOUR_OF_DAY]
        val minute = calendar[Calendar.MINUTE]

        timePickerDialog = TimePickerDialog(requireActivity(),listener,hour,minute,true)
        timePickerDialog.setMessage("Zeit einstellen")
        return timePickerDialog
    }

    companion object {
        val TAG: String = TimePickerFragment::class.java.simpleName
    }

}
