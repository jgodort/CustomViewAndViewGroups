package com.jgodort.customviews

import android.content.Context
import android.util.AttributeSet
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView

class CustomListItem : ViewGroup {

  lateinit var iconView: ImageView
  lateinit var titleTextView: TextView
  lateinit var subtitleTextView: TextView

  constructor(context: Context?) : super(context)

  constructor(
    context: Context?,
    attrs: AttributeSet?
  ) : super(context, attrs)

  constructor(
    context: Context?,
    attrs: AttributeSet?,
    defStyleAttr: Int
  ) : super(context, attrs, defStyleAttr)

  constructor(
    context: Context?,
    attrs: AttributeSet?,
    defStyleAttr: Int,
    defStyleRes: Int
  ) : super(context, attrs, defStyleAttr, defStyleRes)

  override fun onFinishInflate() {
    super.onFinishInflate()
    iconView = findViewById(R.id.icon)
    titleTextView = findViewById(R.id.title)
    subtitleTextView = findViewById(R.id.subtitle)
  }

  private fun measureView(view: View): Pair<Int, Int> {

    val layoutParams = view.layoutParams as MarginLayoutParams

    val widthUsed = view.measuredWidth
    +layoutParams.leftMargin
    +layoutParams.rightMargin

    val heightUsed = view.measuredHeight
    +layoutParams.topMargin
    +layoutParams.bottomMargin

    return Pair(widthUsed, heightUsed)
  }

  override fun onMeasure(
    widthMeasureSpec: Int,
    heightMeasureSpec: Int
  ) {
    measureChildWithMargins(iconView, widthMeasureSpec, 0, heightMeasureSpec, 0)

    var lp = iconView.layoutParams as ViewGroup.MarginLayoutParams
    val iconWidthUsed = iconView.measuredWidth + lp.leftMargin + lp.rightMargin
    val iconHeightUsed = iconView.measuredHeight + lp.topMargin + lp.bottomMargin

    measureChildWithMargins(titleTextView, widthMeasureSpec, iconWidthUsed, heightMeasureSpec, 0)

    lp = titleTextView.layoutParams as ViewGroup.MarginLayoutParams
    val titleWidthUsed = titleTextView.measuredWidth + lp.leftMargin + lp.rightMargin
    val titleHeightUsed = titleTextView.measuredHeight + lp.topMargin + lp.bottomMargin

    measureChildWithMargins(
        subtitleTextView, widthMeasureSpec, iconWidthUsed, heightMeasureSpec, titleHeightUsed
    )

    lp = subtitleTextView.layoutParams as ViewGroup.MarginLayoutParams
    val subtitleWidthUsed = subtitleTextView.measuredWidth + lp.leftMargin + lp.rightMargin
    val subtitleHeightUsed = subtitleTextView.measuredHeight + lp.topMargin + lp.bottomMargin

    // At this point all the child views have been measured.

    // Now calculate the measured width and height of this CustomListItem.

    val width = iconWidthUsed + Math.max(titleWidthUsed, subtitleWidthUsed) +
        paddingLeft + paddingRight
    val height = Math.max(iconHeightUsed, titleHeightUsed + subtitleHeightUsed) +
        paddingTop + paddingBottom

    setMeasuredDimension(
        View.resolveSize(width, widthMeasureSpec), View.resolveSize(height, heightMeasureSpec)
    )
  }

  override fun onLayout(
    changed: Boolean,
    left: Int,
    top: Int,
    right: Int,
    bottom: Int
  ) {

    val iconLayoutParams = iconView.layoutParams as MarginLayoutParams
    val iconLeft = paddingLeft + iconLayoutParams.leftMargin
    val iconTop = paddingTop + iconLayoutParams.topMargin
    val iconRight = iconLeft + iconView.measuredWidth
    val iconBottom = iconTop + iconView.measuredHeight

    iconView.layout(iconLeft, iconTop, iconRight, iconBottom)

    val iconRightPlusMargin = iconRight + iconLayoutParams.rightMargin

    val titleLayoutParams = titleTextView.layoutParams as MarginLayoutParams
    val titleLeft = iconRightPlusMargin + titleLayoutParams.leftMargin
    val titleTop = paddingTop + titleLayoutParams.topMargin
    val titleRight = titleLeft + titleTextView.measuredWidth
    val titleBottom = titleTop + titleTextView.measuredHeight

    titleTextView.layout(titleLeft, titleTop, titleRight, titleBottom)

    val titleBottomPlusMargin = titleBottom + titleLayoutParams.bottomMargin

    val subtitleLayoutParams = subtitleTextView.layoutParams as MarginLayoutParams
    val subtitleLeft = iconRightPlusMargin + subtitleLayoutParams.leftMargin
    val subtitleTop = titleBottomPlusMargin + subtitleLayoutParams.topMargin
    val subtitleRight = subtitleLeft + subtitleTextView.measuredWidth
    val subtitleBottom = subtitleTop + subtitleTextView.measuredHeight

    subtitleTextView.layout(subtitleLeft, subtitleTop, subtitleRight, subtitleBottom)
  }

  override fun checkLayoutParams(params: LayoutParams): Boolean {
    return params is MarginLayoutParams
  }

  override fun generateDefaultLayoutParams(): LayoutParams {
    return MarginLayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT)
  }

  override fun generateLayoutParams(params: LayoutParams): LayoutParams {
    return MarginLayoutParams(params)
  }

  override fun generateLayoutParams(attrs: AttributeSet): LayoutParams {
    return MarginLayoutParams(context, attrs)
  }

}
