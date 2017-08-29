package xyz.ojk.www.mvvmtestapp.main.view

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.support.v7.widget.GridLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.View
import com.zum.answer.answerzum.dagger.DaggerActivityComponent
import kotlinx.android.synthetic.main.activity_main.*
import xyz.ojk.www.mvvmtestapp.App
import xyz.ojk.www.mvvmtestapp.BaseActivity
import xyz.ojk.www.mvvmtestapp.R
import xyz.ojk.www.mvvmtestapp.main.MainViewModel
import xyz.ojk.www.mvvmtestapp.main.adapter.MainPhotoRecyclerAdapter
import javax.inject.Inject



class MainActivity : BaseActivity()  {


    private val viewModel: MainViewModel by lazy { ViewModelProviders.of(this).get(MainViewModel::class.java) }
    @Inject lateinit var adapter: MainPhotoRecyclerAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        DaggerActivityComponent.builder()
                .appComponent((application as App).component())
                .build()
                .inject(this)

        recyclerView.let {
            it.adapter = adapter
            it.layoutManager = GridLayoutManager(this, 2)

            it.addOnScrollListener(object : RecyclerView.OnScrollListener() {
                override fun onScrolled(recyclerView: RecyclerView?, dx: Int, dy: Int) {
                    super.onScrolled(recyclerView, dx, dy)
                    viewModel.onScrolled(recyclerView, dx, dy)
                }
            })
        }

        viewModel.getPhotos().observe(this, Observer {
            it -> adapter.addItems(it!!)
        })

        viewModel.isLoading().observe(this, Observer {
            it -> when(it) {
                true -> progressBar.visibility = View.VISIBLE
                false -> progressBar.visibility = View.INVISIBLE
            }
        })


    }

    fun startDetailActivityByPhoto() {

    }
}
