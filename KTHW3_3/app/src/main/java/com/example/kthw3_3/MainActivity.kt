package com.example.kthw3_3

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.os.Message
import android.widget.Button
import android.widget.SeekBar
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    private var progressRabbit = 0
    private var progressTurtle = 0

    private lateinit var btnStart: Button
    private lateinit var sbRabbit: SeekBar
    private lateinit var sbTurtle: SeekBar

    private val handler = Handler(Looper.getMainLooper()) { msg ->
        when (msg.what) {
            1 -> sbRabbit.progress = progressRabbit
            2 -> sbTurtle.progress = progressTurtle
        }

        if (progressRabbit >= 100 && progressTurtle < 100) {
            Toast.makeText(this, "兔子勝", Toast.LENGTH_SHORT).show()
            btnStart.isEnabled = true
        } else if (progressTurtle >= 100 && progressRabbit < 100) {
            Toast.makeText(this, "烏龜勝", Toast.LENGTH_SHORT).show()
            btnStart.isEnabled = true
        }
        false
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        btnStart = findViewById(R.id.btnStart)
        sbRabbit = findViewById(R.id.sbRabbit)
        sbTurtle = findViewById(R.id.sbTurtle)

        btnStart.setOnClickListener {
            btnStart.isEnabled = false
            progressRabbit = 0
            progressTurtle = 0
            sbRabbit.progress = 0
            sbTurtle.progress = 0
            runRabbit()
            runTurtle()
        }
    }

    private fun runRabbit() {
        Thread {
            val sleepProbability = arrayOf(true, true, false)
            while (progressRabbit <= 100 && progressTurtle < 100) {
                try {
                    Thread.sleep(100)
                    if (sleepProbability.random()) Thread.sleep(300)
                } catch (e: InterruptedException) {
                    e.printStackTrace()
                }
                progressRabbit += 3

                val msg = Message.obtain()
                msg.what = 1
                handler.sendMessage(msg)
            }
        }.start()
    }

    private fun runTurtle() {
        Thread {
            while (progressTurtle <= 100 && progressRabbit < 100) {
                try {
                    Thread.sleep(100)
                } catch (e: InterruptedException) {
                    e.printStackTrace()
                }
                progressTurtle += 1

                val msg = Message.obtain()
                msg.what = 2
                handler.sendMessage(msg)
            }
        }.start()
    }
}
