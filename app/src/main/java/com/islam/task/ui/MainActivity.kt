package com.islam.task.ui

import android.content.res.Configuration
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.islam.task.R
import com.islam.task.data.db.AppDatabase
import com.islam.task.generalUtils.*
import com.islam.task.ui.manufacturer.ManufacturerViewModel
import com.islam.task.ui.manufacturer.ManufacturerViewModelFactory
import com.yarolegovich.slidingrootnav.SlidingRootNav
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.toolbar.*
import org.kodein.di.KodeinAware
import org.kodein.di.android.kodein
import org.kodein.di.generic.instance

class MainActivity : AppCompatActivity(), KodeinAware {

    override val kodein by kodein()

    private lateinit var manufacturerViewModel: ManufacturerViewModel
    private val factory: ManufacturerViewModelFactory by instance()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)


        setContentView(R.layout.activity_main)

        manufacturerViewModel = ViewModelProvider(this, factory).get(ManufacturerViewModel::class.java)

        setSupportActionBar(toolbar)

    }

    override fun applyOverrideConfiguration(overrideConfiguration: Configuration?) {
        if (overrideConfiguration != null) {
            val uiMode = overrideConfiguration.uiMode
            overrideConfiguration.setTo(baseContext.resources.configuration)
            overrideConfiguration.uiMode = uiMode
        }
        super.applyOverrideConfiguration(overrideConfiguration)
    }

    fun showHideAppbar(visibility: Int) {
        mainAppbar.visibility = visibility
    }

    fun setAppbarTitle(string: String) {
        appBarTitle.text = string
    }


}