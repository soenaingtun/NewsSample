package com.newssample.view.features.news.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.newssample.view.core.interactor.UseCase
import com.newssample.view.core.platform.BaseViewModel
import com.newssample.view.features.news.model.PopularNews
import com.newssample.view.features.news.usecase.GetNewsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class NewsViewModel
@Inject
    constructor(private val getNews: GetNewsUseCase) : BaseViewModel() {

        private val _popularNews: MutableLiveData<PopularNews> = MutableLiveData()
        val popularNews: LiveData<PopularNews> = _popularNews

        fun getPopularNews(q: String, apiKey: String, pageSize: Int) =
            getNews(GetNewsUseCase.Params(q,apiKey,pageSize), viewModelScope) {
                it.fold(::handleFailure, ::handleNewsList) }

        private fun handleNewsList(news: PopularNews) {
           _popularNews.value = news
        }
    }
