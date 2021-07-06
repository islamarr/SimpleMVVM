package com.islam.task.ui.splash

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.islam.task.R
import com.islam.task.generalUtils.MyTaskParameters
import com.islam.task.ui.MainActivity
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import org.kodein.di.KodeinAware
import org.kodein.di.android.x.kodein
import org.kodein.di.generic.instance

class SplashFragment : Fragment(), KodeinAware {

    override val kodein by kodein()

    companion object {
        fun newInstance() = SplashFragment()
    }

    private val d: MyTaskParameters by instance()
    private lateinit var viewModel: SplashViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_splash, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(SplashViewModel::class.java)

        (activity as MainActivity).showHideAppbar(View.GONE)

        startApp()
    }

    private fun startApp() {
        lifecycleScope.launch {
            delay(300)

            findNavController().navigate(R.id.action_splash_to_manufacturerFragment)

        }

    }

}