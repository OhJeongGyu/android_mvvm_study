package xyz.ojk.www.mvvmtestapp.dagger

import android.content.Context
import dagger.Component
import dagger.Module
import dagger.Provides
import xyz.ojk.www.mvvmtestapp.App
import xyz.ojk.www.mvvmtestapp.main.view.MainActivity
import javax.inject.Singleton

/**
 * Created by jeonggyuoh on 2017. 8. 28..
 */

@AppScope
@Singleton
@Component(modules = arrayOf(AppModule::class))
interface AppComponent {
    fun provideApplicationContext(): Context
    fun inject(mainActivity: MainActivity)
}

@AppScope
@Singleton
@Module
class AppModule (val app: App) {

    @Provides
    @Singleton
    fun provideApplicationContext(): Context = app

}

