package com.newssample.view.features.news.repository

import com.newssample.view.core.exception.Failure
import com.newssample.view.core.functional.Either
import com.newssample.view.core.platform.NetworkHandler
import com.newssample.view.features.news.model.PopularNews
import com.newssample.view.features.news.model.PopularNewsEntity
import com.newssample.view.features.news.service.NewsService
import retrofit2.Call
import javax.inject.Inject

interface NewsRepository {

    fun getPopularNews(q: String, apiKey: String, pageSize: Int): Either<Failure, PopularNews>

    class Network
    @Inject constructor(
        private val networkHandler: NetworkHandler,
        private val service: NewsService
    ) : NewsRepository {

        override fun getPopularNews(
            q: String,
            apiKey: String,
            pageSize: Int
        ): Either<Failure, PopularNews> {

            return when (networkHandler.isNetworkAvailable()) {
                true -> request(
                    service.getPopularNews(q,apiKey,pageSize),
                    { it.toPopularNews()  },
                    PopularNewsEntity.empty
                )
                false -> Either.Left(Failure.NetworkConnection)
            }
        }



        private fun <T, R> request(
            call: Call<T>,
            transform: (T) -> R,
            default: T
        ): Either<Failure, R> {
            return try {
                val response = call.execute()
                when (response.isSuccessful) {
                    true -> Either.Right(transform((response.body() ?: default)))
                    false -> Either.Left(Failure.ServerError)
                }
            } catch (exception: Throwable) {
                Either.Left(Failure.ServerError)
            }
        }



}
}