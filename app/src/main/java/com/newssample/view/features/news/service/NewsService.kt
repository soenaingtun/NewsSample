package com.newssample.view.features.news.service

import com.newssample.view.features.news.model.PopularNewsEntity
import com.newssample.view.features.news.network.NewsApi
import retrofit2.Call
import retrofit2.Retrofit
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class NewsService
@Inject constructor(retrofit: Retrofit) : NewsApi {
    private val newsApi by lazy { retrofit.create(NewsApi::class.java) }
    override fun getPopularNews(q: String, apiKey: String, pageSize: Int) = newsApi.getPopularNews(q,apiKey,pageSize)

    //override fun getPopularNews(query: String) = newsApi.getPopularNews(query)
}
