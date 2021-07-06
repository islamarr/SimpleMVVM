package com.islam.task.ui.manufacturer

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.islam.task.R
import com.islam.task.generalUtils.MyTaskParameters
import com.islam.task.generalUtils.Utils
import com.islam.task.ui.MainActivity
import kotlinx.android.synthetic.main.manufacturer_fragment.*
import org.kodein.di.KodeinAware
import org.kodein.di.android.x.kodein
import org.kodein.di.generic.instance


class ManufacturerFragment : Fragment(), KodeinAware, View.OnClickListener {

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


        loginButton.setOnClickListener(this)

    }

    override fun onClick(v: View) {
        when (v.id) {
            R.id.loginButton -> {
                Utils.hideKeyboard(requireActivity())
                loadingProgressBar.visibility = View.VISIBLE

                findNavController().navigate(R.id.action_manufacturerFragment_to_carTypesFragment)
            }
        }
    }

}