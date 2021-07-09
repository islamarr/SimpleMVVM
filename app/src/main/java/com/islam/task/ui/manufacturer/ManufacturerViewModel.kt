package com.islam.task.ui.manufacturer

import androidx.lifecycle.ViewModel
import androidx.paging.Pager
import androidx.paging.PagingConfig
import com.islam.task.data.repositories.ManufacturerRepository
import com.islam.task.generalUtils.Const

class ManufacturerViewModel(private val manufacturerRepository: ManufacturerRepository) :
    ViewModel() {

    var manufacturerList =
        Pager(
            config = PagingConfig(
                pageSize = Const.PAGE_SIZE,
                enablePlaceholders = false,
                initialLoadSize = Const.PAGE_SIZE
            ),
            pagingSourceFactory = {
                ManufacturerDataSource(manufacturerRepository)
            }
        ).flow

}