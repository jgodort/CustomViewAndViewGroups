package com.jgodort.customviews

import android.os.Bundle
import android.view.View
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_measured_spec.*

class MeasuredSpecActivity : AppCompatActivity() {

    companion object {
        const val SHOW_ALL_WIDTH_HEIGHT = true
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_measured_spec)

        timer_a.viewTreeObserver.addOnGlobalLayoutListener {
            updateLayoutParamInfo(frame_a, timer_a, layout_params_a, dimensions_a)
            updateLayoutParamInfo(frame_b, timer_b, layout_params_b, dimensions_b)
            updateLayoutParamInfo(frame_c, timer_c, layout_params_c, dimensions_c)
            updateLayoutParamInfo(frame_d, timer_d, layout_params_d, dimensions_d)
        }
    }

    override fun onResume() {
        super.onResume()
        timer_a.start()
        timer_b.start()
        timer_c.start()
        timer_d.start()
    }

    override fun onPause() {
        super.onPause()
        timer_a.stop()
        timer_b.stop()
        timer_c.stop()
        timer_d.stop()
    }


    private fun updateLayoutParamInfo(
        parent: View,
        timerView: View,
        infoTextView: TextView,
        dimensionsTextView: TextView
    ) {

        val parentLayoutParams = parent.layoutParams
        val timerLayoutParams = timerView.layoutParams

        infoTextView.text = getString(
            R.string.parent_timer_layout_params_format,
            MeasureUtils.layoutParamToString(resources, parentLayoutParams.width),
            MeasureUtils.layoutParamToString(resources, parentLayoutParams.height),
            MeasureUtils.layoutParamToString(resources, timerLayoutParams.width),
            MeasureUtils.layoutParamToString(resources, timerLayoutParams.height)
        )

        if (SHOW_ALL_WIDTH_HEIGHT) {
            dimensionsTextView.text = getString(
                R.string.timer_all_width_height,
                MeasureUtils.pixelsToDpString(resources, timerView.measuredWidth),
                MeasureUtils.pixelsToDpString(resources, timerView.measuredHeight),
                MeasureUtils.pixelsToDpString(resources, timerView.width),
                MeasureUtils.pixelsToDpString(resources, timerView.height)
            )
        } else {
            dimensionsTextView.text = getString(
                R.string.timer_width_height,
                MeasureUtils.pixelsToDpString(resources, timerView.measuredWidth),
                MeasureUtils.pixelsToDpString(resources, timerView.measuredHeight)
            )
        }
    }
}
