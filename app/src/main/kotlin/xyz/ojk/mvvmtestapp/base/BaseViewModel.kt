package xyz.ojk.mvvmtestapp.base

import android.arch.lifecycle.ViewModel
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable

/**
 * Created by jeonggyu on 2018. 3. 25..
 */
open class BaseViewModel : ViewModel() {

    internal val compositeDisposal: CompositeDisposable = CompositeDisposable()

    override fun onCleared() {
        super.onCleared()
        compositeDisposal.clear()
    }

    internal operator fun plusAssign(disposable: Disposable) {
        compositeDisposal.add(disposable)
    }
}