package xyz.ojk.www.mvvmtestapp.dagger

import dagger.Component
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import xyz.ojk.www.mvvmtestapp.main.MainViewModel
import xyz.ojk.www.mvvmtestapp.network.FlickrService
import javax.inject.Singleton

/**
 * Created by jeonggyuoh on 2017. 8. 28..
 */

@AppScope
@Singleton
@Component(modules = arrayOf(NetworkModule::class))
interface NetworkComponent {
    fun provideFlickrService(): FlickrService
    fun inject(mainViewModel: MainViewModel)
}

@AppScope
@Singleton
@Module
class NetworkModule (val baseUrl: String){

    @Provides
    @Singleton
    fun provideRxJava2CallAdapterFactory(): RxJava2CallAdapterFactory = RxJava2CallAdapterFactory.create()

    @Provides
    @Singleton
    fun provideGsonConverterFactory(): GsonConverterFactory = GsonConverterFactory.create()

    @Provides
    @Singleton
    fun provideRetrofit(rxJava2CallAdapterFactory: RxJava2CallAdapterFactory, gsonConverterFactory: GsonConverterFactory): Retrofit {
        return Retrofit.Builder()
                .baseUrl(baseUrl)
                .addCallAdapterFactory(rxJava2CallAdapterFactory)
                .addConverterFactory(gsonConverterFactory)
                .build()
    }

    @Provides
    @Singleton
    fun provideFlickrService(retrofit: Retrofit): FlickrService = retrofit.create(FlickrService::class.java)
}