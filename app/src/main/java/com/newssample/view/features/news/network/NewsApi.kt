package com.newssample.view.features.news.network

import com.newssample.view.features.news.model.PopularNewsEntity
import com.newssample.view.features.news.util.Urls
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

internal interface NewsApi {

    companion object {
        private const val POPULAR_NEWS = "/v2/everything"
    }

    @GET(POPULAR_NEWS)
    fun getPopularNews(@Query("q") q: String = "apple",
                       @Query("apiKey") apiKey: String = Urls.API_KEY,
                       @Query("pageSize") pageSize: Int = 10): Call<PopularNewsEntity>

//    @GET(MOVIE_DETAILS)
//    fun movieDetails(@Path(PARAM_MOVIE_ID) movieId: Int): Call<MovieDetailsEntity>

}
