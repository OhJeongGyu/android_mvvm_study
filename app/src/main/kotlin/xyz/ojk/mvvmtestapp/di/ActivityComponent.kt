package com.zum.answer.answerzum.dagger

import dagger.Component
import xyz.ojk.mvvmtestapp.di.ActivityModule
import xyz.ojk.mvvmtestapp.di.AppComponent
import xyz.ojk.mvvmtestapp.presentation.detail.DetailActivity
import xyz.ojk.mvvmtestapp.presentation.main.view.MainActivity

/**
 * Created by jeonggyuoh on 2017. 8. 21..
 */

@ActivityScope
@Component(modules = arrayOf(ActivityModule::class),
        dependencies = arrayOf(AppComponent::class))
interface ActivityComponent : AppComponent{
    fun inject(mainActivity: MainActivity)
    fun inject(detailActivity: DetailActivity)
}



