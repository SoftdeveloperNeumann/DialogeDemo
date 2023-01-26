package com.example.dialogedemo

import android.app.DatePickerDialog
import android.app.TimePickerDialog
import android.content.DialogInterface
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.DatePicker
import android.widget.TimePicker
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import com.example.dialogedemo.databinding.ActivityMainBinding
import com.example.dialogedemo.databinding.DialogInsertBinding
import com.google.android.material.snackbar.Snackbar
import java.security.DigestException
import java.text.SimpleDateFormat
import java.time.LocalDate
import java.time.LocalTime
import java.time.format.DateTimeFormatter
import java.util.*

class MainActivity : AppCompatActivity(), DatePickerDialog.OnDateSetListener, TimePickerDialog.OnTimeSetListener {

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

        binding.btnDate.setOnClickListener {
            DatePickerFragment().show(supportFragmentManager,DatePickerFragment.TAG)
        }

        binding.btnTime.setOnClickListener {
            TimePickerFragment().show(supportFragmentManager,TimePickerFragment.TAG)
        }

    }

    override fun onDateSet(view: DatePicker?, year: Int, month: Int, day: Int) {

        val date = if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            LocalDate.of(year, month + 1, day).format(DateTimeFormatter.ofPattern("dd.MM.yyyy"))
        }else{
           val tmpDate =SimpleDateFormat("yyyy-MM-dd").parse("$year-${month - 1}-$day")
            SimpleDateFormat("dd.MM.yyyy", Locale.GERMANY).format(tmpDate)
        }

        binding.tvOutput.text = "Das Datum ist: $date"
    }

    override fun onTimeSet(view: TimePicker?, hour: Int, minute: Int) {
       val time = if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.O){
           LocalTime.of(hour, minute).format(DateTimeFormatter.ofPattern("HH:mm"))
       }else{
           val tmp = SimpleDateFormat("HH:mm").parse(("$hour:$minute"))
           SimpleDateFormat("HH:mm", Locale.GERMANY).format(tmp)
       }
        binding.tvOutput.text = "Die Uhrzeit ist: $time"
    }
}