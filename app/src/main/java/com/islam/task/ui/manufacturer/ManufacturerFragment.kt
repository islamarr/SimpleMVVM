package com.islam.task.ui.manufacturer

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.gson.Gson
import com.islam.task.R
import com.islam.task.data.entity.ItemModel
import com.islam.task.generalUtils.MyTaskParameters
import com.islam.task.ui.MainActivity
import com.islam.task.ui.adapters.ManufacturerAdapter
import kotlinx.android.synthetic.main.manufacturer_fragment.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import org.json.JSONObject
import org.kodein.di.KodeinAware
import org.kodein.di.android.x.kodein
import org.kodein.di.generic.instance
import timber.log.Timber


class ManufacturerFragment : Fragment(), KodeinAware{

    override val kodein by kodein()

    private val d: MyTaskParameters by instance()
    private lateinit var viewModel: ManufacturerViewModel
    private val factory: ManufacturerViewModelFactory by instance()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.manufacturer_fragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        (activity as MainActivity).showHideAppbar(View.VISIBLE)
        (activity as MainActivity).setAppbarTitle(requireActivity().getString(R.string.sign_in))

        viewModel = ViewModelProvider(this, factory).get(ManufacturerViewModel::class.java)

        GlobalScope.launch(Dispatchers.Main) {
            val wkda = viewModel.getManufacturer().wkda

            val gson = Gson()
            val  jsonObject = gson.toJsonTree(wkda).asJsonObject
            val startingJsonObj = JSONObject(jsonObject.toString())
            val arr = convertJsonToArray(startingJsonObj)

            Timber.d(arr.toString())

            Timber.d(arr[0].toString())

            val manufacturerAdapter = ManufacturerAdapter(arr)
            list.layoutManager = LinearLayoutManager(requireActivity())
            list.adapter = manufacturerAdapter
        }



    }

    private fun convertJsonToArray(startingJsonObj: JSONObject): MutableList<ItemModel> {

        val list = mutableListOf<ItemModel>()
        for (key in startingJsonObj.keys()) {
            val value = startingJsonObj.opt(key)
            list.add(ItemModel(key, value as String))
        }
        return list
    }


}