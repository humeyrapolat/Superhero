package com.humeyrapolat.superhero.viewmodel

import android.app.Application
import androidx.lifecycle.MutableLiveData
import com.humeyrapolat.superhero.model.Result
import com.humeyrapolat.superhero.service.SuperHeroesAPIServices
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.observers.DisposableSingleObserver
import io.reactivex.schedulers.Schedulers

/**
 * @author Humeyra Polat
 * @sınce 24.08.2021
 */
class SuperHeroesViewModel(application: Application) : BaseViewModel(application) {
//kullanıcıyı ilgilendirmeyen kısım

    private val superHeroApiServices = SuperHeroesAPIServices()
    private val disposable = CompositeDisposable()
    val superHeroesLiveData = MutableLiveData<List<Result>>()


    fun getData() {
        disposable.add(
            superHeroApiServices.api.GetAllInfo().subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(object : DisposableSingleObserver<List<Result>>() {
                    override fun onSuccess(t: List<Result>) {
                        superHeroesLiveData.value = t
                    }
                    override fun onError(e: Throwable) {
                    }
                })
        )
    }


    override fun onCleared() {
        super.onCleared()
        disposable.clear()
    }
}