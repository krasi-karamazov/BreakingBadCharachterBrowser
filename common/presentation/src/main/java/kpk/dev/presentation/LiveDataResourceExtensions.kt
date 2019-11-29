package kpk.dev.presentation

import androidx.lifecycle.MutableLiveData

fun <T> MutableLiveData<Resource<T>>.setSuccess(data: T) = this.postValue(Resource(ResourceState.SUCCESS, data))

fun <T> MutableLiveData<Resource<T>>.setFailure(message: String? = null) = this.postValue(Resource(ResourceState.FAILURE, this.value?.data, message))

fun <T> MutableLiveData<Resource<T>>.setLoading() = this.postValue(Resource(ResourceState.LOADING, this.value?.data))