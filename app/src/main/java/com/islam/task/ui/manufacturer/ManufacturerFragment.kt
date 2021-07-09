package com.islam.task.ui.manufacturer

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.paging.LoadState
import androidx.recyclerview.widget.LinearLayoutManager
import com.islam.task.R
import com.islam.task.data.entity.ItemModel
import com.islam.task.databinding.MainFragmentBinding
import com.islam.task.generalUtils.SummaryObject
import com.islam.task.ui.BaseFragment
import com.islam.task.ui.NavigateListener
import com.islam.task.ui.adapters.ManufacturerAdapter
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import org.kodein.di.KodeinAware
import org.kodein.di.android.x.kodein
import org.kodein.di.generic.instance


class ManufacturerFragment : BaseFragment<MainFragmentBinding>(), KodeinAware {

    override val kodein by kodein()

    private lateinit var viewModel: ManufacturerViewModel
    private val factory: ManufacturerViewModelFactory by instance()
    private lateinit var manufacturerAdapter: ManufacturerAdapter

    override val bindingInflater: (LayoutInflater, ViewGroup?, Boolean) -> MainFragmentBinding
        get() = MainFragmentBinding::inflate

    override fun setupOnViewCreated(view: View) {

        binding.toolbar.text = getString(R.string.manufacturer)
        binding.search.visibility = View.GONE

        viewModel = ViewModelProvider(this, factory).get(ManufacturerViewModel::class.java)

        initRecyclerView()

        lifecycleScope.launch {
            viewModel.manufacturerList.collectLatest {
                manufacturerAdapter.submitData(it)
            }
        }

        manufacturerAdapter.addLoadStateListener { loadState ->
            if (loadState.refresh is LoadState.Loading) {
                binding.listLayout.loadingProgressBar.visibility = View.VISIBLE
            } else {
                binding.listLayout.emptyList.visibility = View.GONE
                binding.listLayout.loadingProgressBar.visibility = View.GONE

                val errorState = when {
                    loadState.prepend is LoadState.Error -> loadState.prepend as LoadState.Error
                    loadState.append is LoadState.Error -> loadState.append as LoadState.Error
                    loadState.refresh is LoadState.Error -> loadState.refresh as LoadState.Error
                    else -> null
                }
                errorState?.let {
                    binding.listLayout.emptyList.visibility = View.VISIBLE
                    binding.listLayout.emptyList.text = getString(R.string.no_internet_connection)
                }
            }
        }


    }

    private fun initRecyclerView() {
        binding.listLayout.list.apply {
            manufacturerAdapter = ManufacturerAdapter(object : NavigateListener {
                override fun onNavigate(itemModel: ItemModel) {
                    SummaryObject.summaryModel.manufacturerCode = itemModel.key
                    SummaryObject.summaryModel.manufacturerName = itemModel.value

                    findNavController().navigate(R.id.action_manufacturerFragment_to_carTypesFragment)
                }

            })
            layoutManager = LinearLayoutManager(requireActivity())
            adapter = manufacturerAdapter
        }
    }

}