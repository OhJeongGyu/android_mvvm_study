package xyz.ojk.www.mvvmtestapp.detail

import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import xyz.ojk.www.mvvmtestapp.BaseActivity
import xyz.ojk.www.mvvmtestapp.R

class DetailActivity : BaseActivity() {

    private val viewModel: DetailViewModel by lazy { ViewModelProviders.of(this).get(DetailViewModel::class.java) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)
    }
}
