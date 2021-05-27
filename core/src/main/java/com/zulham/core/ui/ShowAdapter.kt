package com.zulham.core.ui

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.zulham.core.R
import com.zulham.core.domain.model.Show
import kotlinx.android.synthetic.main.list_item.view.*

class ShowAdapter : RecyclerView.Adapter<ShowAdapter.ViewHolder>() {

    private val show = ArrayList<Show>()

    fun setData(newListData: List<Show>?) {
        if (newListData == null) return
        show.clear()
        show.addAll(newListData)
        notifyDataSetChanged()
    }

    fun getSwipeData(swipePosition: Int): Show = show[swipePosition]

    private var onItemClickCallback: OnItemClickCallback? = null

    fun setOnItemClickCallback(onItemClickCallback: OnItemClickCallback) {
        this.onItemClickCallback = onItemClickCallback
    }

    interface OnItemClickCallback {
        fun onItemClicked(data: Show)
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
        private val w = 1000
        private val h = 1000
        private val imgUrl = "https://image.tmdb.org/t/p/w300/"

        fun bind(showTime: Show){
            with(itemView){
                Glide.with(context)
                        .load( imgUrl + showTime.img)
                        .error(R.drawable.ic_launcher_foreground)
                        .apply(RequestOptions().override(w, h))
                        .into(img_poster)

                tv_item_date.text = showTime.releaseDate
                tv_item_title.text = showTime.title
                tv_item_overview.text = showTime.description

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