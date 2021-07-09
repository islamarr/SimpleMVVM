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
import com.islam.task.data.entity.ItemModel
import com.islam.task.generalUtils.*
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

class CarDatesFragment : Fragment(), KodeinAware {

    override val kodein by kodein()

    private lateinit var viewModel: CarDatesViewModel
    private val factory: CarDatesViewModelFactory by instance()
    private lateinit var arr: MutableList<ItemModel>

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.main_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        toolbar.text = getString(
            R.string.car_built_date_title,
            SummaryObject.summaryModel.manufacturerName,
            SummaryObject.summaryModel.carType
        )

        search.visibility = View.GONE

        viewModel = ViewModelProvider(this, factory).get(CarDatesViewModel::class.java)

        Coroutines.main {
            try {
                val wkda = viewModel.getCarDates(
                    SummaryObject.summaryModel.manufacturerCode!!.toInt(),
                    SummaryObject.summaryModel.carType!!
                ).wkda

                val gson = Gson()
                val jsonObject = gson.toJsonTree(wkda).asJsonObject
                val stringJsonObj = JSONObject(jsonObject.toString())
                arr = Utils.convertJsonToArray(stringJsonObj)

                emptyList.visibility = View.GONE
                loadingProgressBar.visibility = View.GONE

                if (arr.isEmpty()) {
                    emptyList.visibility = View.VISIBLE
                    return@main
                }

                initRecyclerView()

            } catch (e: ApiException) {
                loadingProgressBar.visibility = View.GONE
                emptyList.visibility = View.VISIBLE
                emptyList.text = getString(R.string.error)
            } catch (ne: NoInternetException) {
                loadingProgressBar.visibility = View.GONE
                emptyList.visibility = View.VISIBLE
                emptyList.text = getString(R.string.no_internet_connection)
            }
        }

    }

    private fun initRecyclerView() {
        list.apply {
            val mainAdapter = MainAdapter(arr, object : NavigateListener {
                override fun onNavigate(itemModel: ItemModel) {
                    SummaryObject.summaryModel.carDate = itemModel.key

                    findNavController().navigate(R.id.action_carDatesFragment_to_summaryFragment)

                }

            })
            layoutManager = LinearLayoutManager(requireActivity())
            adapter = mainAdapter
        }
    }

}