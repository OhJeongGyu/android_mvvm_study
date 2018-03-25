package xyz.ojk.mvvmtestapp

import android.app.Application
import xyz.ojk.mvvmtestapp.di.AppComponent
import xyz.ojk.mvvmtestapp.di.AppModule
import xyz.ojk.mvvmtestapp.di.DaggerAppComponent
import xyz.ojk.mvvmtestapp.di.NetworkModule


/**
 * Created by jeonggyuoh on 2017. 8. 28..
 */
class App : Application() {

    private var component: AppComponent? = null

    override fun onCreate() {
        super.onCreate()
        component()
    }

    fun component() = component ?: DaggerAppComponent.builder()
            .appModule(AppModule(this))
            .networkModule(NetworkModule())
            .build()
}