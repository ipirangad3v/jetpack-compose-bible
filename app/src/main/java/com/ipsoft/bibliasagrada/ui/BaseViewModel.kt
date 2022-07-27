package com.ipsoft.bibliasagrada.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.ipsoft.bibliasagrada.domain.core.exception.Failure

open class BaseViewModel : ViewModel() {
    private val _failure: MutableLiveData<Failure> = MutableLiveData()
    private val _loading: MutableLiveData<Boolean> = MutableLiveData()

    val failure: LiveData<Failure> = _failure
    val loading: LiveData<Boolean> = _loading

    protected fun handleFailure(failure: Failure) {
        handleLoading(false)
        _failure.postValue(failure)
    }

    protected fun handleLoading(isLoading: Boolean) {
        _loading.postValue(isLoading)
    }
}
