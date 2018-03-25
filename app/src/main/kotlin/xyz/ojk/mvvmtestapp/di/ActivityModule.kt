package xyz.ojk.mvvmtestapp.di

import android.support.v7.app.AppCompatActivity
import com.zum.answer.answerzum.dagger.ActivityScope
import dagger.Module
import dagger.Provides

/**
 * Created by jeonggyu on 2018. 3. 24..
 */
@ActivityScope
@Module
class ActivityModule(val activity: AppCompatActivity) {

    @Provides
    fun provideActivity(): AppCompatActivity {
        return activity
    }

}