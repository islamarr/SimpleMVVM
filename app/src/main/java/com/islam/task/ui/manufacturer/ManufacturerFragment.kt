package com.islam.task.ui.manufacturer

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


class ManufacturerFragment : Fragment(), KodeinAware {

    override val kodein by kodein()

    private lateinit var viewModel: ManufacturerViewModel
    private val factory: ManufacturerViewModelFactory by instance()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.main_fragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        toolbar.text = "Manufacturer"
        search.visibility = View.GONE

        viewModel = ViewModelProvider(this, factory).get(ManufacturerViewModel::class.java)

        GlobalScope.launch(Dispatchers.Main) {
            val wkda = viewModel.getManufacturer().wkda

            val gson = Gson()
            val jsonObject = gson.toJsonTree(wkda).asJsonObject
            val startingJsonObj = JSONObject(jsonObject.toString())
            val arr = Utils.convertJsonToArray(startingJsonObj)

            val mainAdapter = MainAdapter(arr, object : NavigateListener {
                override fun onNavigate(itemModel: ItemModel) {
                    SummaryObject.summaryModel.manufacturerCode = itemModel.key
                    SummaryObject.summaryModel.manufacturerName = itemModel.value

                    findNavController().navigate(R.id.action_manufacturerFragment_to_carTypesFragment)
                }

            })
            loadingProgressBar.visibility = View.GONE
            list.layoutManager = LinearLayoutManager(requireActivity())
            list.adapter = mainAdapter
        }

    }

}