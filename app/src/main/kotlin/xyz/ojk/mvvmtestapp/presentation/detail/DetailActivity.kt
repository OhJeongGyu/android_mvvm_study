package xyz.ojk.mvvmtestapp.presentation.detail

import android.content.Context
import android.os.Bundle
import com.bumptech.glide.Glide
import com.zum.answer.answerzum.dagger.ActivityComponent
import com.zum.answer.answerzum.dagger.DaggerActivityComponent
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.activity_detail.*
import org.jetbrains.anko.intentFor
import org.jetbrains.anko.singleTop
import xyz.ojk.mvvmtestapp.App
import xyz.ojk.mvvmtestapp.base.BaseActivity
import xyz.ojk.mvvmtestapp.R
import javax.inject.Inject

class DetailActivity : BaseActivity() {


    companion object {
        fun startActivity(context: Context, url: String) {
            context.startActivity(context.intentFor<DetailActivity>("URL" to url).singleTop())
        }
    }

    @Inject lateinit var viewModel: DetailViewModel

    private val component: ActivityComponent? = null

    fun component() = component ?: DaggerActivityComponent.builder()
            .appComponent((application as App).component())
            .build()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)
        component().inject(this)
        bindData()
    }

    private fun bindData() {

        viewModel.initUrl(intent.getStringExtra("URL"))

        viewModel += viewModel.urlSubject
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({ Glide.with(this).load(it).into(photoView) }, Throwable::printStackTrace)
    }
}
