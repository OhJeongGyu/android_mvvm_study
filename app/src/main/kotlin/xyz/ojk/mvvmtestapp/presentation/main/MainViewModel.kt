package xyz.ojk.mvvmtestapp.presentation.main

import io.reactivex.schedulers.Schedulers
import io.reactivex.subjects.BehaviorSubject
import io.reactivex.subjects.PublishSubject
import io.reactivex.subjects.Subject
import xyz.ojk.mvvmtestapp.base.BaseViewModel
import xyz.ojk.mvvmtestapp.domain.FlickrRepository
import xyz.ojk.mvvmtestapp.data.entity.Photo
import javax.inject.Inject

/**
 * Created by jeonggyuoh on 2017. 8. 28..
 */

class MainViewModel @Inject constructor(private val flickrRepository: FlickrRepository): BaseViewModel() {

    val photos: Subject<MutableList<Photo>> = PublishSubject.create()

    val isLoading: Subject<Boolean> = BehaviorSubject.createDefault(false)

    private var pageIndex = 0

    init {
        loadData()
    }


    fun loadData() {
        compositeDisposal.add(isLoading.filter { it }
                .flatMap { flickrRepository.getPhotosFromApi(pageIndex++).toObservable() }
                .subscribeOn(Schedulers.io())
                .observeOn(Schedulers.io())
                .doOnSubscribe { isLoading.onNext(true) }
                .doOnError{ isLoading.onNext(false) }
                .subscribe({ photos.onNext(it.photo) }, Throwable::printStackTrace))
    }



}
