package com.islam.task.ui.summary

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.islam.task.R
import com.islam.task.databinding.SummaryFragmentBinding
import com.islam.task.generalUtils.SummaryObject
import com.islam.task.ui.BaseFragment

class SummaryFragment : BaseFragment<SummaryFragmentBinding>() {

    override val bindingInflater: (LayoutInflater, ViewGroup?, Boolean) -> SummaryFragmentBinding
        get() = SummaryFragmentBinding::inflate

    override fun setupOnViewCreated(view: View) {
        val it = SummaryObject.summaryModel

        binding.summaryText.text = getString(
            R.string.summary_details,
            it.manufacturerName,
            it.manufacturerCode,
            it.carType,
            it.carDate
        )
    }

}