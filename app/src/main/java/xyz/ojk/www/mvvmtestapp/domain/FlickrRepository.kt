package xyz.ojk.www.mvvmtestapp.domain

import io.reactivex.Single
import xyz.ojk.www.mvvmtestapp.domain.data.Photos
import xyz.ojk.www.mvvmtestapp.network.FlickrService
import javax.inject.Inject

/**
 * Created by jeonggyuoh on 2017. 8. 28..
 */
class FlickrRepository @Inject constructor(val flickrService: FlickrService){

    private val METHOD_GET_RECENT = "flickr.photos.getRecent"
    private val API_KEY = "<API_KEY>"

    fun getPhotosFromApi(page: Int): Single<Photos> = flickrService.getPhotos(METHOD_GET_RECENT, API_KEY, page, 20, "json", 1).map { it -> it.photos }

//    fun getPhotosFromRealm(page: Int): Single<Photos> {
//        return Single.just(realm.where(RealmPhotos::class.java).equalTo("page", page).findFirst().toPhotos()) ?: getPhotosFromApi(page)

//        return Single.just(realm.where(RealmPhotos::class.java).equalTo("page", page).findFirst().toPhotos())


}