package com.example.deviceinfo

import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView

class MainActivity : AppCompatActivity() {
    private lateinit var descricao: TextView
    private lateinit var botao: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        this.descricao = findViewById(R.id.description)
        this.botao = findViewById(R.id.button)

        this.botao.setOnClickListener({modelo(it)})
    }

    fun modelo(view: View){
        this.descricao.text = Build.MODEL
    }
}