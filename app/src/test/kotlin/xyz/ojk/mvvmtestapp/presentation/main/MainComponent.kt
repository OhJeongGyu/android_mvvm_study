package xyz.ojk.mvvmtestapp.presentation.main

import dagger.Component
import xyz.ojk.mvvmtestapp.di.AppModule
import xyz.ojk.mvvmtestapp.di.NetworkModule
import javax.inject.Singleton

/**
 * Created by jeonggyu on 2018. 3. 25..
 */

@Singleton
@Component(modules = arrayOf(NetworkModule::class))
interface MainComponent {
    fun inject(mainViewModelTest: MainViewModelTest)
    fun inject(mainPhotoRecyclerViewAdapterTest: MainPhotoRecyclerViewAdapterTest)
}