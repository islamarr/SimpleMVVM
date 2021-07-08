package com.islam.task.ui.carTypes

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.gson.Gson
import com.islam.task.R
import com.islam.task.generalUtils.Utils
import com.islam.task.ui.adapters.MainAdapter
import kotlinx.android.synthetic.main.car_types_fragment.*
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
    private lateinit var manufacturerId: String

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.car_types_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        viewModel = ViewModelProvider(this, factory).get(CarTypesViewModel::class.java)

        arguments.let {
            if (it != null) {
                manufacturerId = arguments?.getString("manufacturer")!!
            }
        }


        GlobalScope.launch(Dispatchers.Main) {
            val wkda = viewModel.getMainCarTypes(manufacturerId.toInt()).wkda

            val gson = Gson()
            val jsonObject = gson.toJsonTree(wkda).asJsonObject
            val startingJsonObj = JSONObject(jsonObject.toString())
            val arr = Utils.convertJsonToArray(startingJsonObj)

            val mainAdapter = MainAdapter(arr, 1)
            carTypeList.layoutManager = LinearLayoutManager(requireActivity())
            carTypeList.adapter = mainAdapter
        }

    }

}