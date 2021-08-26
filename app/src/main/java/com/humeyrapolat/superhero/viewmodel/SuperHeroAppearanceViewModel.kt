package com.humeyrapolat.superhero.viewmodel

import android.app.Application
import androidx.lifecycle.MutableLiveData
import com.humeyrapolat.superhero.model.Result
import com.humeyrapolat.superhero.service.SuperHeroesAPIServices
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.observers.DisposableSingleObserver
import io.reactivex.schedulers.Schedulers

class SuperHeroAppearanceViewModel(application: Application) : BaseViewModel(application) {

    var superHeroAppearanceLiveData = MutableLiveData<Result>()
    val loadingLiveData =  MutableLiveData<Boolean>()
    private val superHeroApiServices = SuperHeroesAPIServices()
    private val disposable = CompositeDisposable()

    //fonk oluştur

    fun getIdData(resultId: Long) {
        disposable.add(
            superHeroApiServices.api.getId(resultId).subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(object : DisposableSingleObserver<Result>() {
                    override fun onSuccess(t: Result) {
                        loadingLiveData.value = false
                        superHeroAppearanceLiveData.value= t
                        println(superHeroAppearanceLiveData.value)
                    }
                    override fun onError(e: Throwable) {
                        loadingLiveData.value=true
                        println(e.message)
                    }
                })
        )
    }

    override fun onCleared() {
        super.onCleared()
        disposable.clear()
    }
}