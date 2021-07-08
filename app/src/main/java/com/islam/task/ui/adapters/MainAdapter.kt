package com.islam.task.ui.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.islam.task.R
import com.islam.task.data.entity.ItemModel
import com.islam.task.ui.NavigateListener
import kotlinx.android.synthetic.main.one_item_list.view.*


class MainAdapter(
    private var list: MutableList<ItemModel>,
    private val navigateListener: NavigateListener
) :
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
        holder.text.text = "${listItems.value}"

        if (position % 2 == 0) holder.row.setBackgroundColor(
            ContextCompat.getColor(
                holder.itemView.context,
                R.color.gray
            )
        )
        else holder.row.setBackgroundColor(
            ContextCompat.getColor(
                holder.itemView.context,
                R.color.white
            )
        )

        holder.row.setOnClickListener {
            navigateListener.onNavigate(listItems)
        }

    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var text: TextView = itemView.label
        var row: LinearLayout = itemView.row
    }
}