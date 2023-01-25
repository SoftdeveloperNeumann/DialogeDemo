package com.example.dialogedemo

import android.content.DialogInterface
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import com.example.dialogedemo.databinding.ActivityMainBinding
import com.example.dialogedemo.databinding.DialogInsertBinding
import com.google.android.material.snackbar.Snackbar

class MainActivity : AppCompatActivity() {

    lateinit var binding: ActivityMainBinding
    lateinit var dialogBinding: DialogInsertBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnToast.setOnClickListener { view : View ->
            Toast.makeText(this, "ein simpler Toast", Toast.LENGTH_SHORT).show()
        }

        binding.btnDelete.setOnClickListener { view:View ->
            val forUndo = binding.tvOutput.text
            binding.tvOutput.text = ""
            val sb = Snackbar.make(binding.rootLayout,"Löschen rückgängig machen",Snackbar.LENGTH_LONG)
            sb.setAction("UNDO"){
                binding.tvOutput.text = forUndo
            }
            sb.show()
        }

        binding.btnInsert.setOnClickListener {
            val builder = AlertDialog.Builder(this)
            dialogBinding = DialogInsertBinding.inflate(layoutInflater)
            // Dialog zusammenbauen

            builder.setView(dialogBinding.root)
                .setTitle("Texteingabe")

                .setPositiveButton("OK"){dialog: DialogInterface, which:Int ->
                    binding.tvOutput.text = dialogBinding.editTextTextPersonName.text
                }
                .setNegativeButton("cancel"){ dialog: DialogInterface, which: Int ->
                    dialog.cancel()
                }
                .setNeutralButton("noch ne Funktion"){ dialog: DialogInterface, which: Int ->

                }
                .create()
                .show()
        }

    }
}