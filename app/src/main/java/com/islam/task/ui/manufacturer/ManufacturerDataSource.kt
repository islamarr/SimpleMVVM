package com.islam.task.ui.manufacturer

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.google.gson.Gson
import com.islam.task.data.entity.ItemModel
import com.islam.task.data.repositories.ManufacturerRepository
import com.islam.task.generalUtils.Const
import com.islam.task.generalUtils.Utils
import org.json.JSONObject
import timber.log.Timber

class ManufacturerDataSource(private val repository: ManufacturerRepository) :
    PagingSource<Int, ItemModel>() {

    companion object {
        private const val START_INDEX = 0
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, ItemModel> {
        val pos = params.key ?: START_INDEX

        return try {

            val wkda = repository.getManufacturer(pos, Const.PAGE_SIZE).wkda
            val gson = Gson()
            val jsonObject = gson.toJsonTree(wkda).asJsonObject
            val stringJsonObj = JSONObject(jsonObject.toString())
            val arr = Utils.convertJsonToArray(stringJsonObj)

            LoadResult.Page(
                arr,
                if (pos <= START_INDEX) null else pos - 1,
                if (arr.isEmpty()) null else pos + 1
            )
        } catch (exception: Exception) {
            Timber.e(exception.message.toString())
            LoadResult.Error(exception)
        }
    }

    override fun getRefreshKey(state: PagingState<Int, ItemModel>): Int {
        return 0
    }


}