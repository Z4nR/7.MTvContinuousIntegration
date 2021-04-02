package com.zulham.mtv.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.zulham.mtv.R
import com.zulham.mtv.data.ShowEntity
import kotlinx.android.synthetic.main.activity_main.view.*
import kotlinx.android.synthetic.main.list_item.view.*

class ShowAdapter(private val show: ArrayList<ShowEntity>) : RecyclerView.Adapter<ShowAdapter.ViewHolder>() {

    private var onItemClickCallback: OnItemClickCallback? = null

    fun setOnItemClickCallback(onItemClickCallback: OnItemClickCallback) {
        this.onItemClickCallback = onItemClickCallback
    }

    interface OnItemClickCallback {
        fun onItemClicked(data: ShowEntity)
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
        private val w = 1000
        private val h = 1000

        fun bind(showTime: ShowEntity){
            with(itemView){
                Glide.with(context)
                        .load(showTime.img)
                        .apply(RequestOptions().override(w, h))
                        .into(img_poster)
                
                tv_item_date.text = showTime.releaseDate
                tv_item_title.text = showTime.title
                tv_item_genre.text = showTime.genre

                itemView.setOnClickListener { onItemClickCallback?.onItemClicked(showTime) }
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val itemView = LayoutInflater.from(parent.context)
                .inflate(R.layout.list_item, parent, false)
        return ViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(show[position])
    }

    override fun getItemCount(): Int = show.size
}