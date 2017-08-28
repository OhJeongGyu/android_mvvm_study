package xyz.ojk.www.mvvmtestapp

import android.arch.lifecycle.LifecycleRegistry
import android.arch.lifecycle.LifecycleRegistryOwner
import android.support.v7.app.AppCompatActivity

/**
 * Created by jeonggyuoh on 2017. 8. 28..
 */
open class BaseActivity : AppCompatActivity(), LifecycleRegistryOwner {

    private val lifeCycleRegistry: LifecycleRegistry by lazy { LifecycleRegistry(this) }
    override fun getLifecycle(): LifecycleRegistry = lifeCycleRegistry

}