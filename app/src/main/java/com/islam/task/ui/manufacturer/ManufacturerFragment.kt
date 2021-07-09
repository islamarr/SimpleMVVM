package com.islam.task.ui.manufacturer

import android.opengl.Visibility
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.paging.LoadState
import androidx.recyclerview.widget.LinearLayoutManager
import com.islam.task.R
import com.islam.task.data.entity.ItemModel
import com.islam.task.generalUtils.SummaryObject
import com.islam.task.ui.NavigateListener
import com.islam.task.ui.adapters.ManufacturerAdapter
import kotlinx.android.synthetic.main.item_list.*
import kotlinx.android.synthetic.main.main_fragment.*
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import org.kodein.di.KodeinAware
import org.kodein.di.android.x.kodein
import org.kodein.di.generic.instance


class ManufacturerFragment : Fragment(), KodeinAware {

    override val kodein by kodein()

    private lateinit var viewModel: ManufacturerViewModel
    private val factory: ManufacturerViewModelFactory by instance()
    private lateinit var manufacturerAdapter: ManufacturerAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.main_fragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        toolbar.text = getString(R.string.manufacturer)
        search.visibility = View.GONE

        viewModel = ViewModelProvider(this, factory).get(ManufacturerViewModel::class.java)

        initRecyclerView()

        lifecycleScope.launch {
            viewModel.manufacturerList.collectLatest {
                manufacturerAdapter.submitData(it)
            }
        }

        manufacturerAdapter.addLoadStateListener { loadState ->
            if (loadState.refresh is LoadState.Loading){
                loadingProgressBar.visibility = View.VISIBLE
            }
            else{
                emptyList.visibility = View.GONE
                loadingProgressBar.visibility = View.GONE

                val errorState = when {
                    loadState.prepend is LoadState.Error -> loadState.prepend as LoadState.Error
                    loadState.append is LoadState.Error -> loadState.append as LoadState.Error
                    loadState.refresh is LoadState.Error -> loadState.refresh as LoadState.Error
                    else -> null
                }
                errorState?.let {
                    emptyList.visibility = View.VISIBLE
                    emptyList.text = getString(R.string.no_internet_connection)
                }
            }
        }


    }

    private fun initRecyclerView() {
        list.apply {
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