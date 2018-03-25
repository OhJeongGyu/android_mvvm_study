package xyz.ojk.mvvmtestapp.di

import android.content.Context
import dagger.Module
import dagger.Provides
import xyz.ojk.mvvmtestapp.App
import javax.inject.Singleton

/**
 * Created by jeonggyu on 2018. 3. 24..
 */
@Singleton
@Module
class AppModule (val app: App) {

    @Provides
    @Singleton
    fun provideApplicationContext(): Context = app

}

