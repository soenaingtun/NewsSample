package com.newssample.view.features.news.model

import com.newssample.view.core.extension.empty

data class PopularNews(val popularArticles: List<PopularArticle>,
                       val status: String,
                       val totalResults: Int){
    companion object{
        val empty = PopularNews(emptyList(),String.empty(),0)
    }
}
