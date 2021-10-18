package com.rahul.acro.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.rahul.acro.base.ApiService
import com.rahul.acro.model.AcroResponse

import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.observers.DisposableSingleObserver
import io.reactivex.schedulers.Schedulers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class AcroViewModel : ViewModel() {

    private val apiService = ApiService()
    private val disposable = CompositeDisposable()
    private var _response = MutableLiveData<List<AcroResponse>>()

    var response: LiveData<List<AcroResponse>> = _response
    val loading = MutableLiveData<Boolean>()
    val loadingError = MutableLiveData<Boolean>()


     fun findAcro(shortFormText: String){
        loading.value = true
        loadingError.value = false
        disposable.add(
            apiService.getAcroList(shortFormText)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(object : DisposableSingleObserver<List<AcroResponse>>() {
                    override fun onSuccess(t: List<AcroResponse>) {
                        _response.value = t
                        loading.value = false
                        loadingError.value = false
                    }

                    override fun onError(e: Throwable) {
                        loading.value = false
                        loadingError.value = true
                    }

                })
        )


    }

}