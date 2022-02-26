package com.newssample.view.features.news.model

import com.google.gson.annotations.SerializedName
import com.newssample.view.core.extension.empty

data class PopularNewsEntity(@SerializedName("articles")
                                val popularArticles: List<PopularArticle>,
                             val status: String,
                             val totalResults: Int){

    companion object{
        val empty = PopularNewsEntity(emptyList(),String.empty(),0)
    }

    fun toPopularNews() = PopularNews(popularArticles,status,totalResults)
}