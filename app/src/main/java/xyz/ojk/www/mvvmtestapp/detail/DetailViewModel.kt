package xyz.ojk.www.mvvmtestapp.detail

import android.arch.lifecycle.ViewModel
import io.reactivex.disposables.CompositeDisposable

/**
 * Created by jeonggyuoh on 2017. 8. 29..
 */
class DetailViewModel : ViewModel() {

    private val compositeDisposal: CompositeDisposable = CompositeDisposable()

    override fun onCleared() {
        super.onCleared()
        compositeDisposal.clear()
    }


}