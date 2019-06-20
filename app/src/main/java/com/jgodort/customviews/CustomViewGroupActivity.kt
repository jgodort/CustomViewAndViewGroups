package com.jgodort.customviews

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.activity_custom_view_group.list

class CustomViewGroupActivity : AppCompatActivity() {

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(R.layout.activity_custom_view_group)

    list.layoutManager = LinearLayoutManager(this)
    list.adapter = ListItemAdapter().apply {
      items = listOf(
          ListItem(R.drawable.ic_launcher_foreground, "TITLE_1", "SUBTITLE_1"),
          ListItem(R.drawable.ic_launcher_foreground, "LARGE_TITLE_2", "LARGE_SUBTITLE_2")
      )
    }
  }
}
