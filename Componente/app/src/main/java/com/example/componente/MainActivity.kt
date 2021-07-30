package com.example.componente

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.CalendarView

class MainActivity : AppCompatActivity() {
    private lateinit var calendario: CalendarView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        this.calendario = findViewById(R.id.calendarView)
        this.calendario.setOnDateChangeListener(DateChange())
    }

    inner class DateChange: CalendarView.OnDateChangeListener{
        override fun onSelectedDayChange(
            view: CalendarView,
            dia: Int,
            mes: Int,
            ano: Int){
            Log.i("LOG", "${dia}/${mes}/${ano}")
        }
    }
}