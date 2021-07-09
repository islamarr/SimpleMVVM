package com.islam.task.ui.summary

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.islam.task.R
import com.islam.task.generalUtils.SummaryObject
import kotlinx.android.synthetic.main.summary_fragment.*

class SummaryFragment : Fragment() {


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.summary_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        val it = SummaryObject.summaryModel

        summaryText.text = getString(
            R.string.summary_details,
            it.manufacturerName,
            it.manufacturerCode,
            it.carType,
            it.carDate
        )

    }

}