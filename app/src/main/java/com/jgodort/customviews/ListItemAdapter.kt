package com.jgodort.customviews

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class ListItemAdapter : RecyclerView.Adapter<ListItemAdapter.ViewHolder>() {

  var items = emptyList<ListItem>()

  override fun onCreateViewHolder(
    parent: ViewGroup,
    viewType: Int
  ): ViewHolder {
    return ViewHolder(
        LayoutInflater.from(parent.context).inflate(R.layout.list_item_custom, parent, false)
    )
  }

  override fun getItemCount(): Int = items.size

  override fun onBindViewHolder(
    holder: ViewHolder,
    position: Int
  ) {
    holder.bind(items[position])
  }

  inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    private val icon = itemView.findViewById<ImageView>(R.id.icon)
    private val title = itemView.findViewById<TextView>(R.id.title)
    private val subtitle = itemView.findViewById<TextView>(R.id.subtitle)

    fun bind(item: ListItem) {
      icon.setImageResource(item.iconResId)
      title.text = item.title
      subtitle.text = item.subtitle
    }
  }
}