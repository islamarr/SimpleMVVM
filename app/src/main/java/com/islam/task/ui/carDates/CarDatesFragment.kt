package com.islam.task.ui.carDates

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
import com.islam.task.generalUtils.Utils
import com.islam.task.ui.MainActivity
import com.islam.task.ui.adapters.MainAdapter
import kotlinx.android.synthetic.main.car_dates_fragment.*
import kotlinx.android.synthetic.main.car_types_fragment.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import org.json.JSONObject
import org.kodein.di.KodeinAware
import org.kodein.di.android.x.kodein
import org.kodein.di.generic.instance

class CarDatesFragment : Fragment(), KodeinAware {

    override val kodein by kodein()

    private lateinit var carType: String

    private lateinit var viewModel: CarDatesViewModel
    private val factory: CarDatesViewModelFactory by instance()

            override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.car_dates_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        viewModel = ViewModelProvider(this, factory).get(CarDatesViewModel::class.java)


        arguments.let {
            if (it != null) {
                carType = arguments?.getString("carType")!!
            }
        }

        GlobalScope.launch(Dispatchers.Main) {
            val wkda = viewModel.getCarDates(130 , carType).wkda

            val gson = Gson()
            val jsonObject = gson.toJsonTree(wkda).asJsonObject
            val startingJsonObj = JSONObject(jsonObject.toString())
            val arr = Utils.convertJsonToArray(startingJsonObj)

            val mainAdapter = MainAdapter(requireActivity(), arr, 2)
            carDatesList.layoutManager = LinearLayoutManager(requireActivity())
            carDatesList.adapter = mainAdapter
        }

    }

}