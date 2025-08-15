package com.rdo.analitica

import android.app.DatePickerDialog
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import android.widget.Button
import android.widget.EditText
import java.util.Calendar
import android.content.Context

class Step1Activity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_step1)
        val n1 = findViewById<EditText>(R.id.field_319135040)
        val n2 = findViewById<EditText>(R.id.field_1446507836)
        val n3 = findViewById<EditText>(R.id.field_1087205519)
        val n4 = findViewById<EditText>(R.id.field_122891259)

        // Load saved draft
        val prefs = getSharedPreferences("rdo_draft", Context.MODE_PRIVATE)
        n1.setText(prefs.getString("319135040", ""))
        n2.setText(prefs.getString("1446507836", ""))
        n3.setText(prefs.getString("1087205519", ""))
        n4.setText(prefs.getString("122891259", ""))

        n4.setOnClickListener {
            val c = Calendar.getInstance()
            val dp = DatePickerDialog(this, { _, y, m, d ->
                val mm = (m+1).toString().padStart(2,'0')
                val dd = d.toString().padStart(2,'0')
                val s = "$y-$mm-$dd"
                n4.setText(s)
            }, c.get(Calendar.YEAR), c.get(Calendar.MONTH), c.get(Calendar.DAY_OF_MONTH))
            dp.show()
        }

        findViewById<Button>(R.id.nextBtn).setOnClickListener {
            // Basic validation: number required
            if (n1.text.toString().trim().isEmpty()) {
                n1.error = "Campo obrigatório"
                return@setOnClickListener
            }
            // save draft
            val ed = prefs.edit()
            ed.putString("319135040", n1.text.toString())
            ed.putString("1446507836", n2.text.toString())
            ed.putString("1087205519", n3.text.toString())
            ed.putString("122891259", n4.text.toString())
            ed.apply()

            val i = Intent(this, Step2Activity::class.java)
            i.putExtra("319135040", n1.text.toString())
            i.putExtra("1446507836", n2.text.toString())
            i.putExtra("1087205519", n3.text.toString())
            i.putExtra("122891259", n4.text.toString())
            startActivity(i)
        }
    }
}
