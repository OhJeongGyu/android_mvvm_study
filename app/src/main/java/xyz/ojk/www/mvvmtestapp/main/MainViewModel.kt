package xyz.ojk.www.mvvmtestapp.main

import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import android.support.v7.widget.GridLayoutManager
import android.support.v7.widget.RecyclerView
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import xyz.ojk.www.mvvmtestapp.dagger.DaggerNetworkComponent
import xyz.ojk.www.mvvmtestapp.dagger.NetworkModule
import xyz.ojk.www.mvvmtestapp.domain.FlickrRepository
import xyz.ojk.www.mvvmtestapp.domain.data.Photo
import javax.inject.Inject

/**
 * Created by jeonggyuoh on 2017. 8. 28..
 */

class MainViewModel: ViewModel() {

    @Inject lateinit var flickrRepository: FlickrRepository

    private val compositeDisposal: CompositeDisposable = CompositeDisposable()

    private var photos: MutableLiveData<MutableList<Photo>> = MutableLiveData()

    private var isLoading: MutableLiveData<Boolean> = MutableLiveData()

    private var page_index = 0;

    init {
        DaggerNetworkComponent.builder().networkModule(NetworkModule("https://api.flickr.com/"))
                .build()
                .inject(this)
        isLoading.value = false
        loadData()
    }

    override fun onCleared() {
        super.onCleared()
        compositeDisposal.clear()
    }

    fun getPhotos(): MutableLiveData<MutableList<Photo>> = photos
    fun isLoading(): MutableLiveData<Boolean> = isLoading

    fun loadData() {
        isLoading.value = true
        compositeDisposal.add(flickrRepository.getPhotosFromApi(page_index++)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({
                    photoResult -> (photos?.value ?: mutableListOf()).let {
                        it.addAll(photoResult.photo)
                        photos.value = it
                        isLoading.value = false
                    }
                }, {
                    error -> error.printStackTrace()
                    isLoading.value = false
                }))
    }

    fun onScrolled(recyclerView: RecyclerView?, dx: Int, dy: Int) {
        val visibleItemCount: Int = recyclerView?.childCount!!
        val totalItemCount: Int = recyclerView?.layoutManager?.itemCount!!
        val firstVisibleItem: Int = (recyclerView?.layoutManager as GridLayoutManager).findFirstVisibleItemPosition()

        if(!(isLoading.value!!) && (totalItemCount - visibleItemCount)< firstVisibleItem + 5) loadData()
    }

}
