package com.islam.task.ui.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.islam.task.R
import com.islam.task.data.entity.ItemModel
import com.islam.task.ui.NavigateListener
import kotlinx.android.synthetic.main.one_item_list.view.*


class MainAdapter(var list: MutableList<ItemModel>, val navigateListener: NavigateListener) :
    RecyclerView.Adapter<MainAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            LayoutInflater.from(parent.context)
                .inflate(R.layout.one_item_list, parent, false)
        )
    }

    override fun getItemCount(): Int {
        return list.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val listItems = list[position]
        holder.text.text = "${listItems.key} -- ${listItems.value}"
        holder.row.setOnClickListener { view ->
            navigateListener.onNavigate(listItems)
        }

    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var text = itemView.label
        var row = itemView.row
    }
}