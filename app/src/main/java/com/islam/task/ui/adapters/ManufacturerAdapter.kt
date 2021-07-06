package com.islam.task.ui.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.islam.task.R
import com.islam.task.data.entity.ItemModel
import kotlinx.android.synthetic.main.one_item_manufacturer.view.*

class ManufacturerAdapter(var list: MutableList<ItemModel>) :
    RecyclerView.Adapter<ManufacturerAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            LayoutInflater.from(parent.context)
                .inflate(R.layout.one_item_manufacturer, parent, false)
        )
    }

    override fun getItemCount(): Int {
        return list.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val listItems = list[position]
        holder.text.text = "${listItems.key} -- ${listItems.value}"

    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var text = itemView.label
    }
}