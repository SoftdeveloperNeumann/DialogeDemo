package com.example.dialogedemo


import android.app.Dialog
import android.content.Context
import android.content.DialogInterface
import android.os.Bundle
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.DialogFragment
import com.example.dialogedemo.databinding.DialogInsertBinding

class AlertDialogFragment: DialogFragment() {

    private var listener: DialogInterface.OnClickListener?=null
    private lateinit var binding: DialogInsertBinding

    override fun onAttach(context: Context) {
        super.onAttach(context)
        if(context is DialogInterface.OnClickListener)
            listener = context
    }

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        binding = DialogInsertBinding.inflate(layoutInflater)
        val builder = AlertDialog.Builder(requireActivity())

        builder.setView(binding.root)
            .setTitle("Text eingeben")
            .setPositiveButton("ok",listener)
            .setNegativeButton("cancel",listener)
            .setNeutralButton("nothing",listener)

        return builder.create()
    }


    companion object{
        val TAG = AlertDialogFragment::class.java.simpleName
    }
}