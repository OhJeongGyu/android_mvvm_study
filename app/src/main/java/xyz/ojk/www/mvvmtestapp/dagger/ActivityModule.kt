package com.zum.answer.answerzum.dagger

import android.support.v7.app.AppCompatActivity
import dagger.Module
import dagger.Provides

/**
 * Created by jeonggyuoh on 2017. 8. 21..
 */
@Module
class ActivityModule(val activity: AppCompatActivity) {

    @ActivityScope
    @Provides
    fun provideActivity(): AppCompatActivity {
        return activity
    }

}