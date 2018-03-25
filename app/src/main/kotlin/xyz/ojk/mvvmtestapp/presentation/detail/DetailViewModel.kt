package xyz.ojk.mvvmtestapp.presentation.detail

import android.content.Context
import io.reactivex.subjects.BehaviorSubject
import io.reactivex.subjects.Subject
import xyz.ojk.mvvmtestapp.base.BaseViewModel
import javax.inject.Inject

/**
 * Created by jeonggyuoh on 2017. 8. 29..
 */
class DetailViewModel @Inject constructor(private val context: Context) : BaseViewModel() {

    val urlSubject: Subject<String> = BehaviorSubject.create()

    fun initUrl(url: String) {
        urlSubject.onNext(url)
    }

}