package com.rdo.analitica

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import android.widget.Button
import android.widget.TextView
import okhttp3.FormBody
import okhttp3.OkHttpClient
import okhttp3.Request
import kotlin.concurrent.thread
import android.widget.Toast
import android.content.Context

class ReviewActivity : AppCompatActivity() {
    // Google Forms POST endpoint (formResponse)
    private val FORM_URL = "https://docs.google.com/forms/d/e/1FAIpQLScDh78CnfCBkflyH2IlcYjr1h1il5hp3iZ4wmrc5jUHjHYVQA/formResponse"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_review)
        val summary = findViewById<TextView>(R.id.summary)
        val send = findViewById<Button>(R.id.sendBtn)

        // Collect all extras
        val extras = intent.extras
        val map = HashMap<String,String>()
        val sb = StringBuilder()
        var missingRequired = false
        if (extras != null) {
            val keys = extras.keySet()
            for (k in keys) {
                val v = extras.getString(k) ?: ""
                map[k] = v
                sb.append("$k : $v\n")
            }
        }
        summary.text = sb.toString()

        // Basic check: require the main number field (319135040)
        if (map["319135040"].isNullOrEmpty()) {
            Toast.makeText(this, "Campo Número está vazio. Volte e preencha.", Toast.LENGTH_LONG).show()
            missingRequired = true
        }

        send.isEnabled = !missingRequired

        send.setOnClickListener {
            // Build form body mapping entry.ids to fields
            val formBodyBuilder = FormBody.Builder()
            for ((k,v) in map) {
                formBodyBuilder.add("entry.$k", v)
            }

            val client = OkHttpClient()
            val request = Request.Builder()
                .url(FORM_URL)
                .post(formBodyBuilder.build())
                .build()

            thread {
                try {
                    val resp = client.newCall(request).execute()
                    runOnUiThread {
                        if (resp.isSuccessful) {
                            // clear draft
                            val prefs = getSharedPreferences("rdo_draft", Context.MODE_PRIVATE)
                            prefs.edit().clear().apply()
                            Toast.makeText(this, "Enviado com sucesso (HTTP " + resp.code + ")", Toast.LENGTH_LONG).show()
                        } else {
                            Toast.makeText(this, "Resposta do servidor: " + resp.code, Toast.LENGTH_LONG).show()
                        }
                    }
                } catch (e: Exception) {
                    runOnUiThread {
                        Toast.makeText(this, "Falha ao enviar: " + e.message, Toast.LENGTH_LONG).show()
                    }
                }
            }
        }
    }
}
