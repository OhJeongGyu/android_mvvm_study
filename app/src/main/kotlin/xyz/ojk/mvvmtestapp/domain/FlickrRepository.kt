package xyz.ojk.mvvmtestapp.domain

import io.reactivex.Single
import xyz.ojk.mvvmtestapp.data.entity.Photos
import xyz.ojk.mvvmtestapp.data.api.FlickrService
import javax.inject.Inject

/**
 * Created by jeonggyuoh on 2017. 8. 28..
 */
class FlickrRepository @Inject constructor(val flickrService: FlickrService){

    private val METHOD_GET_RECENT = "flickr.photos.getRecent"
    private val API_KEY = "289e8b0e7a1ce218cb74ac08dffd103f"

    fun getPhotosFromApi(page: Int): Single<Photos> = flickrService.getPhotos(METHOD_GET_RECENT, API_KEY, page, 20, "json", 1).map { it -> it.photos }


}