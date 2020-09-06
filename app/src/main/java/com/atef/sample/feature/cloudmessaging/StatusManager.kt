package com.atef.sample.feature.cloudmessaging

import androidx.lifecycle.LiveData
import androidx.lifecycle.MediatorLiveData
import com.atef.sample.base.state.Resource
import javax.inject.Inject

class StatusManager @Inject constructor() {

    private val _status: MediatorLiveData<Resource<CaseStatus?>> = MediatorLiveData()
    val status: LiveData<Resource<CaseStatus?>> = _status

    fun updateStatus(resource: Resource<CaseStatus?>, forceUpdate: Boolean = false) {
        if (forceUpdate) _status.postValue(resource)
        else if (resource.data != status.value?.data) _status.postValue(resource)
    }
}
