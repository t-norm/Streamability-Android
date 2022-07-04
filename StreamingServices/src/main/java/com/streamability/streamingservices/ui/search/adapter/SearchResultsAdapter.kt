package com.streamability.streamingservices.ui.search.adapter


import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.streamability.datalayer.domain.models.searchMovie.Result
import com.streamability.streamingservices.R

class SearchResultsAdapter(): RecyclerView.Adapter<SearchResultsAdapter.ViewHolder>() {
    private lateinit var searchList: List<Result>

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.search_results_card_design, parent, false)

        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val movieItem = searchList[position]

        holder.imageView.loadImage(movieItem.backdrop_path)
        holder.textView.text = "${movieItem.title} (${movieItem.release_date.substring(0,4)})"
    }

    override fun getItemCount(): Int {
        return searchList.size
    }

    class ViewHolder(ItemView: View) : RecyclerView.ViewHolder(ItemView) {
        val imageView: ImageView = itemView.findViewById(R.id.search_results_card_imageview)
        val textView: TextView = itemView.findViewById(R.id.search_results_card_textview)
    }

    fun applyData(data: List<Result>) {
        searchList = data
    }
}

private fun ImageView.loadImage(url: String) {
    val baseUrl = "https://www.themoviedb.org/t/p/w1280/"
    Glide.with(context).load(baseUrl + url).into(this)
}