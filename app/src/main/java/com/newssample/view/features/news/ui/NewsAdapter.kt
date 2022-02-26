package com.newssample.view.features.news.ui

import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.newssample.view.R
import com.newssample.view.core.extension.inflate
import com.newssample.view.core.extension.loadFromUrl
import com.newssample.view.core.navigation.Navigator
import com.newssample.view.features.news.model.PopularArticle
import com.newssample.view.features.news.util.Utility
import javax.inject.Inject
import kotlin.properties.Delegates

class NewsAdapter
@Inject constructor() : RecyclerView.Adapter<NewsAdapter.ViewHolder>() {

    internal var collection: List<PopularArticle> by Delegates.observable(emptyList()) { _, _, _ ->
        notifyDataSetChanged()
    }

    internal var clickListener: (PopularArticle) -> Unit = { _, -> }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        ViewHolder(parent.inflate(R.layout.news_item))

    override fun onBindViewHolder(viewHolder: ViewHolder, position: Int) =
        viewHolder.bind(collection[position], clickListener)

    override fun getItemCount() = collection.size

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(article: PopularArticle, clickListener: (PopularArticle) -> Unit) {

            val itemImage: ImageView = itemView.findViewById(R.id.itemImage)
            val titleText: TextView = itemView.findViewById(R.id.titleLabel)
            val bodyText: TextView = itemView.findViewById(R.id.bodyLabel)
            val dateText: TextView = itemView.findViewById(R.id.dateLabel)
            val authorText: TextView = itemView.findViewById(R.id.authorLabel)

            itemImage.loadFromUrl(article.urlToImage.toString())
            titleText.text = article.title
            bodyText.text = article.content
            authorText.text = article.author
            dateText.text = Utility.formatDate(article.publishedAt.toString())
            itemView.setOnClickListener {
                clickListener(
                    article)

            }
        }
    }
}