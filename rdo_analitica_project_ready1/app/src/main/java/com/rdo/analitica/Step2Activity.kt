package com.rdo.analitica

import android.app.TimePickerDialog
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import android.widget.Button
import android.widget.EditText
import java.util.Calendar
import android.content.Context

class Step2Activity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_step2)
        val eStart = findViewById<EditText>(R.id.field_492976514)
        val eEnd = findViewById<EditText>(R.id.field_1383029341)
        val f1 = findViewById<EditText>(R.id.field_401913462)
        val f2 = findViewById<EditText>(R.id.field_1961506747)
        val action = findViewById<EditText>(R.id.field_975102026)
        val fail = findViewById<EditText>(R.id.field_1957072471)

        // receive previous
        val i = intent
        val map = HashMap<String, String>()
        map["319135040"] = i.getStringExtra("319135040") ?: ""
        map["1446507836"] = i.getStringExtra("1446507836") ?: ""
        map["1087205519"] = i.getStringExtra("1087205519") ?: ""
        map["122891259"] = i.getStringExtra("122891259") ?: ""

        // Load draft for time and others
        val prefs = getSharedPreferences("rdo_draft", Context.MODE_PRIVATE)
        eStart.setText(prefs.getString("492976514",""))
        eEnd.setText(prefs.getString("1383029341",""))
        f1.setText(prefs.getString("401913462",""))
        f2.setText(prefs.getString("1961506747",""))
        action.setText(prefs.getString("975102026",""))
        fail.setText(prefs.getString("1957072471",""))

        eStart.setOnClickListener {
            val c = Calendar.getInstance()
            val tp = TimePickerDialog(this, { _, h, m ->
                val s = h.toString().padStart(2,'0') + ":" + m.toString().padStart(2,'0')
                eStart.setText(s)
            }, c.get(Calendar.HOUR_OF_DAY), c.get(Calendar.MINUTE), true)
            tp.show()
        }
        eEnd.setOnClickListener {
            val c = Calendar.getInstance()
            val tp = TimePickerDialog(this, { _, h, m ->
                val s = h.toString().padStart(2,'0') + ":" + m.toString().padStart(2,'0')
                eEnd.setText(s)
            }, c.get(Calendar.HOUR_OF_DAY), c.get(Calendar.MINUTE), true)
            tp.show()
        }

        findViewById<Button>(R.id.nextBtn2).setOnClickListener {
            // save draft
            val ed = prefs.edit()
            ed.putString("492976514", eStart.text.toString())
            ed.putString("1383029341", eEnd.text.toString())
            ed.putString("401913462", f1.text.toString())
            ed.putString("1961506747", f2.text.toString())
            ed.putString("975102026", action.text.toString())
            ed.putString("1957072471", fail.text.toString())
            ed.apply()

            val intent2 = Intent(this, ReviewActivity::class.java)
            intent2.putExtra("319135040", map["319135040"])
            intent2.putExtra("1446507836", map["1446507836"])
            intent2.putExtra("1087205519", map["1087205519"])
            intent2.putExtra("122891259", map["122891259"])
            intent2.putExtra("492976514", eStart.text.toString())
            intent2.putExtra("1383029341", eEnd.text.toString())
            intent2.putExtra("401913462", f1.text.toString())
            intent2.putExtra("1961506747", f2.text.toString())
            intent2.putExtra("975102026", action.text.toString())
            intent2.putExtra("1957072471", fail.text.toString())
            startActivity(intent2)
        }

        findViewById<Button>(R.id.backBtn2).setOnClickListener {
            finish()
        }
    }
}
