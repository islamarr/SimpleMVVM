package com.islam.task.ui.adapters

import android.app.Activity
import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.FragmentActivity
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.islam.task.R
import com.islam.task.data.entity.ItemModel
import com.islam.task.data.entity.SummaryModel
import com.islam.task.ui.summary.SummaryViewModel
import kotlinx.android.synthetic.main.one_item_list.view.*


class MainAdapter(activity: Activity, var list: MutableList<ItemModel>, var fragmentIndex: Int) :
    RecyclerView.Adapter<MainAdapter.ViewHolder>() {

    var summaryViewModel = ViewModelProvider((activity as FragmentActivity)).get(SummaryViewModel::class.java)
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
            val bundle = Bundle()
            val summaryModel = summaryViewModel.getSummary()
            when (fragmentIndex) {
                0 -> {
                    summaryModel.manufacturerCode = listItems.key
                    summaryModel.manufacturerName = listItems.value
                    summaryViewModel.updateSummary(summaryModel)
                    bundle.putString("manufacturer", listItems.key)
                    view.findNavController()
                        .navigate(R.id.action_manufacturerFragment_to_carTypesFragment, bundle)
                }
                1 -> {
                    summaryModel.carType = listItems.key
                    summaryViewModel.updateSummary(summaryModel)
                    bundle.putString("carType", listItems.key)
                    view.findNavController()
                        .navigate(R.id.action_carTypesFragment_to_carDatesFragment, bundle)
                }
                2 -> {
                    summaryModel.carDate = listItems.key
                    summaryViewModel.updateSummary(summaryModel)
                    bundle.putString("sss", listItems.key)
                    view.findNavController().navigate(R.id.action_carDatesFragment_to_summaryFragment)
                }
            }
        }

    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var text = itemView.label
        var row = itemView.row
    }
}