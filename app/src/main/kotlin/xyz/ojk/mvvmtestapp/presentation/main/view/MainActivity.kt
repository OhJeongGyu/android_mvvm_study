package xyz.ojk.mvvmtestapp.presentation.main.view

import android.os.Bundle
import android.support.v7.widget.GridLayoutManager
import android.view.View
import com.jakewharton.rxbinding2.support.v7.widget.RxRecyclerView
import com.zum.answer.answerzum.dagger.ActivityComponent
import com.zum.answer.answerzum.dagger.DaggerActivityComponent
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.activity_main.*
import xyz.ojk.mvvmtestapp.App
import xyz.ojk.mvvmtestapp.base.BaseActivity
import xyz.ojk.mvvmtestapp.R
import xyz.ojk.mvvmtestapp.presentation.main.MainViewModel
import xyz.ojk.mvvmtestapp.presentation.main.adapter.MainPhotoRecyclerAdapter
import javax.inject.Inject



class MainActivity : BaseActivity()  {


    @Inject lateinit var adapter: MainPhotoRecyclerAdapter
    @Inject lateinit var viewModel: MainViewModel

    private var component: ActivityComponent? = null

    fun component() = component ?: DaggerActivityComponent.builder()
            .appComponent((application as App).component())
            .build()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        component().inject(this)

        initView()
        bindData()

    }

    private fun bindData() {

        viewModel += viewModel.photos
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({ adapter.addItems(it) }, Throwable::printStackTrace)

        viewModel += viewModel.isLoading
                .subscribeOn(Schedulers.io())
                .map { if(it) View.VISIBLE else View.GONE }
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({ progressBar.visibility = it }, Throwable::printStackTrace)
    }

    private fun initView() {

        recyclerView.let {
            it.adapter = adapter
            it.layoutManager = GridLayoutManager(this, 2)
        }

        viewModel += RxRecyclerView.scrollEvents(recyclerView)
                .filter{ recyclerView.childCount - recyclerView.layoutManager.itemCount < (recyclerView.layoutManager.itemCount as GridLayoutManager).findFirstVisibleItemPosition() + 5 }
                .subscribe({ viewModel.loadData() }, Throwable::printStackTrace)

    }

}
