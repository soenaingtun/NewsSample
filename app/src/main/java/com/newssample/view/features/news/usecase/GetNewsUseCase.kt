package com.newssample.view.features.news.usecase

import com.newssample.view.core.interactor.UseCase
 import com.newssample.view.features.news.model.PopularNews
import com.newssample.view.features.news.repository.NewsRepository
import javax.inject.Inject

class GetNewsUseCase
@Inject constructor(private val newsRepository: NewsRepository) :
    UseCase<PopularNews, GetNewsUseCase.Params>() {

    override suspend fun run(params: Params) = newsRepository.getPopularNews(params.q, params.apiKey, params.pageSize)

    data class Params(val q: String,val apiKey: String, val pageSize: Int)
}
