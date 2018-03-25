package xyz.ojk.mvvmtestapp.presentation.main.adapter

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.jakewharton.rxbinding2.view.RxView
import xyz.ojk.mvvmtestapp.R
import xyz.ojk.mvvmtestapp.presentation.detail.DetailActivity
import xyz.ojk.mvvmtestapp.data.entity.Photo
import xyz.ojk.mvvmtestapp.presentation.main.MainViewModel
import java.util.concurrent.TimeUnit
import javax.inject.Inject

/**
 * Created by jeonggyuoh on 2017. 8. 28..
 */
class MainPhotoRecyclerAdapter @Inject constructor(private val context: Context, private val viewModel: MainViewModel) : RecyclerView.Adapter<MainPhotoHolder>() {

    private var items: MutableList<Photo> = mutableListOf()

    override fun onBindViewHolder(holder: MainPhotoHolder, position: Int) {

        holder.textViewOnMainPhotoHolder.text = items[position].title

        Glide.with(context)
                .load(makeThumnailUrl(items[position]))
                .centerCrop()
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .into(holder.imageViewOnMainPhotoHolder)

        viewModel += RxView.clicks(holder.itemView)
                .throttleFirst(300, TimeUnit.MILLISECONDS)
                .map { makeThumnailUrl(items[position]) }
                .subscribe({ DetailActivity.startActivity(context, it) }, Throwable::printStackTrace)

    }

    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): MainPhotoHolder = MainPhotoHolder(LayoutInflater.from(parent?.context).inflate(R.layout.main_photo_holder, parent, false))

    override fun getItemCount(): Int = items.size

    fun addItems(photos: MutableList<Photo>) {
        photos.filter{ photo -> !items.contains(photo) }
                .forEach { it -> items.add(it) }
        notifyDataSetChanged()
    }

    fun makeThumnailUrl(photo : Photo): String{
        return "http://farm${photo.farm}.staticflickr.com/${photo.server}/${photo.id}_${photo.secret}_m.jpg"
    }


}