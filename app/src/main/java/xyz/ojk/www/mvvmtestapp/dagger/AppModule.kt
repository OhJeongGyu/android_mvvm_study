package xyz.ojk.www.mvvmtestapp.dagger

import android.content.Context
import dagger.Module
import dagger.Provides
import xyz.ojk.www.mvvmtestapp.App
import javax.inject.Singleton

/**
 * Created by jeonggyuoh on 2017. 8. 28..
 */

@Singleton
@Module
class AppModule (val app: App) {

    @Provides
    @Singleton
    fun provideApplicationContext(): Context = app

}

