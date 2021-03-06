package com.islam.task.ui.carDates

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

class CarDatesFragment : BaseFragment<MainFragmentBinding>(), KodeinAware {

    override val kodein by kodein()

    private lateinit var viewModel: CarDatesViewModel
    private val factory: CarDatesViewModelFactory by instance()
    private lateinit var arr: MutableList<ItemModel>

    override val bindingInflater: (LayoutInflater, ViewGroup?, Boolean) -> MainFragmentBinding
        get() = MainFragmentBinding::inflate

    override fun setupOnViewCreated(view: View) {

        Utils.hideKeyboard(requireActivity())

        binding.toolbar.text = getString(
            R.string.car_built_date_title,
            SummaryObject.summaryModel.manufacturerName,
            SummaryObject.summaryModel.carType
        )

        binding.search.visibility = View.GONE

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

    private fun initRecyclerView() {
        binding.listLayout.list.apply {
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