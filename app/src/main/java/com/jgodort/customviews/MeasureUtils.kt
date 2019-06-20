package com.jgodort.customviews

import android.content.res.Resources
import android.view.ViewGroup.LayoutParams.MATCH_PARENT
import android.view.ViewGroup.LayoutParams.WRAP_CONTENT

class MeasureUtils {

    companion object {
        fun layoutParamToString(resources: Resources, params: Int) = when (params) {
            MATCH_PARENT -> resources.getString(R.string.match_parent)
            WRAP_CONTENT -> resources.getString(R.string.wrap_content)
            else -> pixelsToDp(resources, params).toString()
        }


        private fun pixelsToDp(resources: Resources, pixels: Int) =
            Math.round(pixels / resources.displayMetrics.density)

        fun pixelsToDpString(resources: Resources, pixels: Int) = pixelsToDp(resources, pixels).toString() + "dp"
    }
}