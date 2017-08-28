package xyz.ojk.www.mvvmtestapp.main.adapter

import android.support.v7.widget.RecyclerView
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import xyz.ojk.www.mvvmtestapp.R


/**
 * Created by jeonggyuoh on 2017. 8. 28..
 */
class MainPhotoHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    var imageViewOnMainPhotoHolder: ImageView = itemView.findViewById(R.id.imageViewOnMainPhotoHolder)
    var textViewOnMainPhotoHolder: TextView = itemView.findViewById(R.id.textViewOnMainPhotoHolder)

}