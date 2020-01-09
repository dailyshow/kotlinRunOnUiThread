package com.cis.kotlinrunonuithread

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.SystemClock
import android.util.Log
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    var isRunning = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        btn.setOnClickListener { view ->
            val time = System.currentTimeMillis()
            tv1.text = "버튼 클릭 : ${time}"
        }

        isRunning = true
        val threadClass = ThreadClass()
        threadClass.start()
    }

    inner class ThreadClass : Thread() {
        override fun run() {
            while (isRunning){
                SystemClock.sleep(1000)
                val time = System.currentTimeMillis()
//                Log.d("thread", "thread : ${time}")

                runOnUiThread {
                    tv2.text = "thread : ${time}"
                }
            }
        }
    }

    override fun onStop() {
        super.onStop()

        isRunning = false
    }
}
