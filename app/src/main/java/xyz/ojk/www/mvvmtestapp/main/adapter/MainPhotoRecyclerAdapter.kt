package xyz.ojk.www.mvvmtestapp.main.adapter

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.jakewharton.rxbinding2.view.RxView
import xyz.ojk.www.mvvmtestapp.R
import xyz.ojk.www.mvvmtestapp.domain.data.Photo
import java.util.concurrent.TimeUnit
import javax.inject.Inject

/**
 * Created by jeonggyuoh on 2017. 8. 28..
 */
class MainPhotoRecyclerAdapter @Inject constructor(val context: Context) : RecyclerView.Adapter<MainPhotoHolder>() {

    private var items: MutableList<Photo> = mutableListOf()

    override fun onBindViewHolder(holder: MainPhotoHolder?, position: Int) {
        holder?.textViewOnMainPhotoHolder?.text = items[position].title

        Glide.with(context)
                .load(makeThumnailUrl(items[position]))
                .centerCrop()
                .diskCacheStrategy(DiskCacheStrategy.ALL)
//                .placeholder(R.drawable.notification_template_icon_bg)
                .into(holder?.imageViewOnMainPhotoHolder)

        RxView.clicks(holder?.itemView!!)
                .throttleFirst(500, TimeUnit.MILLISECONDS)
                .subscribe()

    }

    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): MainPhotoHolder = MainPhotoHolder(LayoutInflater.from(parent?.context).inflate(R.layout.main_photo_holder, parent, false))

    override fun getItemCount(): Int = items.size

    fun addItems(photos: MutableList<Photo>) {
        photos.filter{ photo -> !items.contains(photo) }
                .forEach { it -> items.add(it) }
        notifyDataSetChanged()
    }

    private fun makeThumnailUrl(photo : Photo): String{
        return "http://farm${photo.farm}.staticflickr.com/${photo.server}/${photo.id}_${photo.secret}_m.jpg"
    }


}