package xyz.ojk.mvvmtestapp.di

import android.content.Context
import dagger.Component
import xyz.ojk.mvvmtestapp.data.api.FlickrService
import javax.inject.Singleton

/**
 * Created by jeonggyu on 2018. 3. 24..
 */
@Singleton
@Component(modules = arrayOf(AppModule::class, NetworkModule::class))
interface AppComponent {
    fun provideApplicationContext(): Context
    fun provideFlickrService(): FlickrService
}
