package com.islam.task.ui.carTypes

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.gson.Gson
import com.islam.task.R
import com.islam.task.data.entity.ItemModel
import com.islam.task.generalUtils.SummaryObject
import com.islam.task.generalUtils.Utils
import com.islam.task.ui.NavigateListener
import com.islam.task.ui.adapters.MainAdapter
import kotlinx.android.synthetic.main.item_list.*
import kotlinx.android.synthetic.main.main_fragment.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import org.json.JSONObject
import org.kodein.di.KodeinAware
import org.kodein.di.android.x.kodein
import org.kodein.di.generic.instance

class CarTypesFragment : Fragment(), KodeinAware {

    override val kodein by kodein()


    private lateinit var viewModel: CarTypesViewModel
    private val factory: CarTypesViewModelFactory by instance()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.main_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        toolbar.text = "Car Types >> ${SummaryObject.summaryModel.manufacturerName}"
        search.visibility = View.VISIBLE

        viewModel = ViewModelProvider(this, factory).get(CarTypesViewModel::class.java)

        GlobalScope.launch(Dispatchers.Main) {
            val wkda =
                viewModel.getMainCarTypes(SummaryObject.summaryModel.manufacturerCode!!.toInt()).wkda

            val gson = Gson()
            val jsonObject = gson.toJsonTree(wkda).asJsonObject
            val startingJsonObj = JSONObject(jsonObject.toString())
            val arr = Utils.convertJsonToArray(startingJsonObj)

            val mainAdapter = MainAdapter(arr, object : NavigateListener {
                override fun onNavigate(itemModel: ItemModel) {
                    SummaryObject.summaryModel.carType = itemModel.key
                    findNavController().navigate(R.id.action_carTypesFragment_to_carDatesFragment)
                }

            })
            loadingProgressBar.visibility = View.GONE
            list.layoutManager = LinearLayoutManager(requireActivity())
            list.adapter = mainAdapter
        }

    }

}