package com.islam.task.ui.carTypes

import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.gson.Gson
import com.islam.task.R
import com.islam.task.data.entity.ItemModel
import com.islam.task.databinding.MainFragmentBinding
import com.islam.task.generalUtils.*
import com.islam.task.ui.BaseFragment
import com.islam.task.ui.NavigateListener
import com.islam.task.ui.adapters.MainAdapter
import org.json.JSONObject
import org.kodein.di.KodeinAware
import org.kodein.di.android.x.kodein
import org.kodein.di.generic.instance


class CarTypesFragment : BaseFragment<MainFragmentBinding>(), KodeinAware {

    override val kodein by kodein()

    private lateinit var viewModel: CarTypesViewModel
    private val factory: CarTypesViewModelFactory by instance()
    private lateinit var mainAdapter: MainAdapter
    private lateinit var arr: MutableList<ItemModel>

    override val bindingInflater: (LayoutInflater, ViewGroup?, Boolean) -> MainFragmentBinding
        get() = MainFragmentBinding::inflate

    override fun setupOnViewCreated(view: View) {

        binding.toolbar.text = getString(
            R.string.car_types_title,
            SummaryObject.summaryModel.manufacturerName
        )
        binding.search.visibility = View.VISIBLE

        viewModel = ViewModelProvider(this, factory).get(CarTypesViewModel::class.java)

        Coroutines.main {
            try {
                val wkda =
                    viewModel.getMainCarTypes(SummaryObject.summaryModel.manufacturerCode!!.toInt()).wkda

                val gson = Gson()
                val jsonObject = gson.toJsonTree(wkda).asJsonObject
                val stringJsonObj = JSONObject(jsonObject.toString())
                arr = Utils.convertJsonToArray(stringJsonObj)

                initSearch()

                binding.listLayout.emptyList.visibility = View.GONE
                binding.listLayout.loadingProgressBar.visibility = View.GONE

                if (arr.isEmpty()) {
                    binding.listLayout.emptyList.visibility = View.VISIBLE
                    return@main
                }

                initRecyclerView()

            } catch (e: ApiException) {
                binding.listLayout.loadingProgressBar.visibility = View.GONE
                binding.listLayout.emptyList.visibility = View.VISIBLE
                binding.listLayout.emptyList.text = getString(R.string.error)
            } catch (ne: NoInternetException) {
                binding.listLayout.loadingProgressBar.visibility = View.GONE
                binding.listLayout.emptyList.visibility = View.VISIBLE
                binding.listLayout.emptyList.text = getString(R.string.no_internet_connection)
            }
        }

    }

    private fun initSearch() {
        binding.search.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {}
            override fun afterTextChanged(s: Editable?) {
                filter(s.toString())
            }

        })
    }

    private fun initRecyclerView() {
        binding.listLayout.list.apply {
            mainAdapter = MainAdapter(arr, object : NavigateListener {
                override fun onNavigate(itemModel: ItemModel) {
                    SummaryObject.summaryModel.carType = itemModel.key
                    findNavController().navigate(R.id.action_carTypesFragment_to_carDatesFragment)
                }

            })
            layoutManager = LinearLayoutManager(requireActivity())
            adapter = mainAdapter
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