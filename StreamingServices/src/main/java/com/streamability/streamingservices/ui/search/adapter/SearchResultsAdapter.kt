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

class SearchResultsAdapter(
    private val navigateToDetails: (id: Int) -> Unit,
): RecyclerView.Adapter<SearchResultsAdapter.ViewHolder>() {
    private lateinit var searchResultsList: List<Result>

    class ViewHolder(ItemView: View) : RecyclerView.ViewHolder(ItemView) {
        val imageView: ImageView = itemView.findViewById(R.id.search_results_card_imageview)
        val textViewTitle: TextView = itemView.findViewById(R.id.search_results_card_title)
        val textViewReleaseDate: TextView = itemView.findViewById(R.id.search_results_card_release_date)
        val textViewOverview: TextView = itemView.findViewById(R.id.search_results_card_overview)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.search_results_card_design, parent, false)

        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val movieItem = searchResultsList[position]

        holder.imageView.loadImage(movieItem.poster_path)
        holder.textViewTitle.text = movieItem.title
        holder.textViewReleaseDate.text = movieItem.release_date.substring(0,4)
        holder.textViewOverview.text = movieItem.overview

        holder.itemView.setOnClickListener {
            navigateToDetails(movieItem.id)
        }
    }

    override fun getItemCount(): Int {
        return searchResultsList.size
    }

    fun applyData(data: List<Result>) {
        searchResultsList = data
    }
}

// Glide plugin for loading url based image paths
private fun ImageView.loadImage(url: String) {
    val baseUrl = "https://www.themoviedb.org/t/p/w1280/"
    Glide.with(context).load(baseUrl + url).into(this)
}