package com.islam.task.ui.carDates

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.islam.task.R
import com.islam.task.ui.MainActivity
import kotlinx.android.synthetic.main.car_dates_fragment.*

class CarDatesFragment : Fragment() {

    private lateinit var carType: String

    private lateinit var viewModel: CarDatesViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.car_dates_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        viewModel = ViewModelProvider(this).get(CarDatesViewModel::class.java)


        arguments.let {
            if (it != null) {
                carType = arguments?.getString("carType")!!
            }
        }

        navig.setText(carType)
        navig.setOnClickListener {
            findNavController().navigate(R.id.action_carDatesFragment_to_summaryFragment)
        }

    }

}