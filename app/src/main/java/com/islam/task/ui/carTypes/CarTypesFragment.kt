package com.islam.task.ui.carTypes

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.islam.task.R
import com.islam.task.ui.MainActivity
import kotlinx.android.synthetic.main.car_types_fragment.*

class CarTypesFragment : Fragment() {


    private lateinit var viewModel: CarTypesViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.car_types_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        (activity as MainActivity).showHideAppbar(View.VISIBLE)
        (activity as MainActivity).setAppbarTitle(requireActivity().getString(R.string.reset_password))

        viewModel = ViewModelProvider(this).get(CarTypesViewModel::class.java)


        confirmButton.setOnClickListener {
            findNavController().navigate(R.id.action_carTypesFragment_to_carDatesFragment)
        }
    }

}