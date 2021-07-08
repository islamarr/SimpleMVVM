package com.islam.task.ui.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.islam.task.R
import com.islam.task.data.entity.ItemModel
import com.islam.task.ui.NavigateListener
import kotlinx.android.synthetic.main.one_item_list.view.*


class MainAdapter(
    private var list: MutableList<ItemModel>,
    private val navigateListener: NavigateListener
) : PagingDataAdapter<ItemModel, MainAdapter.ViewHolder>(DIFF_CALLBACK) {

    companion object {

        private val DIFF_CALLBACK = object : DiffUtil.ItemCallback<ItemModel>() {
            override fun areItemsTheSame(oldTripItem: ItemModel, newTripItem: ItemModel): Boolean {

                return oldTripItem.key == newTripItem.key
            }

            override fun areContentsTheSame(oldTripItem: ItemModel, newTripItem: ItemModel): Boolean {

                return oldTripItem == newTripItem
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            LayoutInflater.from(parent.context)
                .inflate(R.layout.one_item_list, parent, false)
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val listItems = getItem(position)

        if (listItems != null) {
            holder.bind(listItems)
        }

    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private var label: TextView = itemView.label
        private var row: LinearLayout = itemView.row

        fun bind(listItems: ItemModel) {
            label.text = "${listItems.value}"

            if (layoutPosition % 2 == 0) row.setBackgroundColor(
                ContextCompat.getColor(
                    itemView.context,
                    R.color.gray
                )
            )
            else row.setBackgroundColor(
                ContextCompat.getColor(
                    itemView.context,
                    R.color.white
                )
            )

            row.setOnClickListener {
                navigateListener.onNavigate(listItems)
            }
        }
    }

    fun updateList(updatedList: MutableList<ItemModel>) {
        list = updatedList
        notifyDataSetChanged()
    }
}