package com.github.khoben.woff2typeface

import android.os.Bundle
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import com.github.khoben.woff2android.Woff2Typeface
import com.github.khoben.woff2android.woff2Typeface
import java.io.File

class MainActivity : AppCompatActivity(R.layout.main_layout) {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val editText = findViewById<EditText>(R.id.text_edit)

        // using extension
        editText.woff2Typeface(this, assets, "test.woff2")
    }
}