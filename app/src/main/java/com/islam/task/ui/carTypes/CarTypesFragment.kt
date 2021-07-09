package com.islam.task.ui.carTypes

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
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


class CarTypesFragment : Fragment(), KodeinAware {

    override val kodein by kodein()

    private lateinit var viewModel: CarTypesViewModel
    private val factory: CarTypesViewModelFactory by instance()
    private lateinit var mainAdapter: MainAdapter
    private lateinit var arr: MutableList<ItemModel>


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

        search.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {}

            override fun afterTextChanged(s: Editable?) {
                filter(s.toString())
            }

        })

        viewModel = ViewModelProvider(this, factory).get(CarTypesViewModel::class.java)

        Coroutines.main {
            try {
                val wkda =
                    viewModel.getMainCarTypes(SummaryObject.summaryModel.manufacturerCode!!.toInt()).wkda

                val gson = Gson()
                val jsonObject = gson.toJsonTree(wkda).asJsonObject
                val startingJsonObj = JSONObject(jsonObject.toString())
                arr = Utils.convertJsonToArray(startingJsonObj)

                emptyList.visibility = View.GONE
                loadingProgressBar.visibility = View.GONE

                if (arr.isEmpty()) {
                    emptyList.visibility = View.VISIBLE
                    return@main
                }
                mainAdapter = MainAdapter(arr, object : NavigateListener {
                    override fun onNavigate(itemModel: ItemModel) {
                        SummaryObject.summaryModel.carType = itemModel.key
                        findNavController().navigate(R.id.action_carTypesFragment_to_carDatesFragment)
                    }

                })



                list.apply {
                    layoutManager = LinearLayoutManager(requireActivity())
                    adapter = mainAdapter
                }
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

    fun filter(text: String?) {
        var tempList: MutableList<ItemModel> = ArrayList()
        if (text!!.isNotEmpty()) {
            for (item in arr) {
                if (item.value.contains(text)) {
                    tempList.add(item)
                }
            }
        } else {
            tempList = arr
        }
        mainAdapter.updateList(tempList)
    }

}