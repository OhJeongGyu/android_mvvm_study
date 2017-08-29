package com.zum.answer.answerzum.dagger

import android.support.v7.app.AppCompatActivity
import dagger.Component
import dagger.Module
import dagger.Provides
import xyz.ojk.www.mvvmtestapp.dagger.AppComponent
import xyz.ojk.www.mvvmtestapp.main.view.MainActivity

/**
 * Created by jeonggyuoh on 2017. 8. 21..
 */

@ActivityScope
@Component(modules = arrayOf(ActivityModule::class),
        dependencies = arrayOf(AppComponent::class))
interface ActivityComponent {
    fun inject(mainActivity: MainActivity)
}

@ActivityScope
@Module
class ActivityModule(val activity: AppCompatActivity) {

    @Provides
    fun provideActivity(): AppCompatActivity {
        return activity
    }

}