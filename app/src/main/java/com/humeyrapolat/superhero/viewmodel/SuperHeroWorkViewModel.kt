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
 * @sınce 26.08.2021
 */
class SuperHeroWorkViewModel(application: Application) : BaseViewModel(application) {

    var superHeroWorkLiveData = MutableLiveData<Result>()


}