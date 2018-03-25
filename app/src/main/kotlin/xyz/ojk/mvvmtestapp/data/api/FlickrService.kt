package xyz.ojk.mvvmtestapp.data.api

import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Query
import xyz.ojk.mvvmtestapp.data.entity.PhotoResult

/**
 * Created by jeonggyuoh on 2017. 8. 28..
 */
interface FlickrService {
    @GET("/services/rest/")
    fun getPhotos(@Query("method") method: String,
                  @Query("api_key") api_key: String,
                  @Query("page") page: Int,
                  @Query("per_page") per_page: Int,
                  @Query("format") format: String,
                  @Query("nojsoncallback") nojsoncallback:Int): Single<PhotoResult>
}