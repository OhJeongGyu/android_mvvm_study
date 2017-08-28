package xyz.ojk.www.mvvmtestapp.dagger

import com.zum.answer.answerzum.dagger.ActivityModule
import com.zum.answer.answerzum.dagger.ActivityScope
import dagger.Component
import xyz.ojk.www.mvvmtestapp.main.view.MainActivity

/**
 * Created by jeonggyuoh on 2017. 8. 28..
 */

@ActivityScope
@Component(modules = arrayOf(ActivityModule::class),
        dependencies = arrayOf(AppComponent::class))
interface ActivityComponent {
    fun inject(mainActivity: MainActivity)
}