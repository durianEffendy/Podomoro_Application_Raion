package com.example.oprecraion

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.CountDownTimer
import android.widget.Button
import android.widget.TextView

class MainActivity : AppCompatActivity() {

    private lateinit var timer : CountDownTimer
    private var isTimerRunning  = false
    private final var START_TIME_IN_MILLIS :Long = 1500500
    private var mTimeLeftInMillis :Long = START_TIME_IN_MILLIS

    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val tvTime = findViewById<TextView>(R.id.tvTimer)
        val btnStartStop = findViewById<Button>(R.id.btnStart)
        val btnReset = findViewById<Button>(R.id.btnReset)

        fun stopTimer(){
            timer.cancel()
            isTimerRunning = false
            btnStartStop.text = "START"
        }

        fun startTimer(){
            timer = object : CountDownTimer (mTimeLeftInMillis.toLong(), 1000){
                override fun onTick(millisUntilFinished: Long) {
                    isTimerRunning = true
                    mTimeLeftInMillis = millisUntilFinished.toLong() + 1000
                    val minutes = millisUntilFinished/1000/60
                    val seconds = millisUntilFinished/1000%60
                    if (minutes<=0){
                        tvTime.text = String.format("%02d", seconds)
                    }else{
                        tvTime.text = String.format("%02d : %02d", minutes, seconds)
                    }

                    btnStartStop.text = "PAUSE"
                }

                override fun onFinish() {
                    isTimerRunning = false
                    tvTime.text = "Kelar"
                    btnStartStop.text = "START"
                }

            }.start()
        }

        btnReset.setOnClickListener {
            mTimeLeftInMillis = START_TIME_IN_MILLIS
            isTimerRunning = false
            tvTime.text = String.format("25 : 00")
            stopTimer()

        }

        btnStartStop.setOnClickListener{
            if (isTimerRunning){
                stopTimer()
            }else{
                startTimer()
            }

        }

    }
}