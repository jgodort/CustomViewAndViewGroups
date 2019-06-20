package com.jgodort.customviews

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_main.timer

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

    }

  override fun onResume() {
    super.onResume()
    timer.start()
  }

  override fun onPause() {
    super.onPause()
    timer.stop()
  }
}
