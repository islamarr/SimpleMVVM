package com.islam.task.ui.summary

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.islam.task.R
import com.islam.task.ui.MainActivity

class SummaryFragment : Fragment() {

    private lateinit var viewModel: SummaryViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.summary_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(SummaryViewModel::class.java)

        (activity as MainActivity).showHideAppbar(View.VISIBLE)
        (activity as MainActivity).setAppbarTitle(requireActivity().getString(R.string.send_sms))

    }

}