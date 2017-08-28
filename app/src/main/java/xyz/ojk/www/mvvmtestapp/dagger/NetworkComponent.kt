package xyz.ojk.www.mvvmtestapp.dagger

import dagger.Component
import xyz.ojk.www.mvvmtestapp.main.MainViewModel
import xyz.ojk.www.mvvmtestapp.network.FlickrService
import javax.inject.Singleton

/**
 * Created by jeonggyuoh on 2017. 8. 28..
 */
@Singleton
@Component(modules = arrayOf(NetworkModule::class))
interface NetworkComponent {
    fun provideFlickrService(): FlickrService
    fun inject(mainViewModel: MainViewModel)
}