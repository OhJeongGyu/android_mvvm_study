package xyz.ojk.www.mvvmtestapp

import android.app.Application
import xyz.ojk.www.mvvmtestapp.dagger.AppComponent
import xyz.ojk.www.mvvmtestapp.dagger.AppModule
import xyz.ojk.www.mvvmtestapp.dagger.DaggerAppComponent


/**
 * Created by jeonggyuoh on 2017. 8. 28..
 */
class App : Application() {

    private lateinit var component: AppComponent

    override fun onCreate() {
        super.onCreate()
        component = DaggerAppComponent.builder()
                .appModule(AppModule(this))
                .build()


    }

    fun component(): AppComponent = component
}