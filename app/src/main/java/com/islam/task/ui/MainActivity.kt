package com.islam.task.ui

import android.content.res.Configuration
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.islam.task.R
import com.islam.task.ui.manufacturer.ManufacturerViewModel
import com.islam.task.ui.manufacturer.ManufacturerViewModelFactory
import kotlinx.android.synthetic.main.activity_main.*
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

        manufacturerViewModel =
            ViewModelProvider(this, factory).get(ManufacturerViewModel::class.java)

        setSupportActionBar(toolbar)

        mainAppbar.visibility = View.GONE

    }


}