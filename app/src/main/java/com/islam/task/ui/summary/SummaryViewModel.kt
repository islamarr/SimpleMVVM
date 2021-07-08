package com.islam.task.ui.summary

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.islam.task.data.entity.SummaryModel

class SummaryViewModel : ViewModel() {

    private val _summary = MutableLiveData<SummaryModel>()
    var summary: LiveData<SummaryModel> = _summary

    fun updateSummary(summaryModel: SummaryModel) {
        _summary.postValue(summaryModel)
    }

    fun getSummary() : SummaryModel {
        return _summary.value!!
    }

}