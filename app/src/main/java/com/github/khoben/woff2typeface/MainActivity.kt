package com.github.khoben.woff2typeface

import android.os.Bundle
import android.view.View
import android.widget.EditText
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import com.github.khoben.woff2android.woff2Typeface

class MainActivity : AppCompatActivity(R.layout.main_layout) {

    private val loadTypeface =
        registerForActivityResult(ActivityResultContracts.OpenDocument()) { uri ->
            if (uri != null) {
                editText.woff2Typeface(
                    contentResolver.openInputStream(uri).use { it!!.readBytes() })
            }
        }

    private lateinit var editText: EditText

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        editText = findViewById(R.id.text_edit)
        findViewById<View>(R.id.load_typeface).setOnClickListener {
            loadTypeface.launch(arrayOf("font/woff2"))
        }
        editText.woff2Typeface(assets, "test.woff2")
    }
}