/**
 * Copyright (C) 2020 Fernando Cejas Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.newssample.view.core.navigation

import android.content.Context
import android.view.View
import androidx.fragment.app.FragmentActivity
import com.newssample.view.features.login.Authenticator
import com.newssample.view.features.login.LoginActivity
import com.newssample.view.features.news.model.PopularArticle
import com.newssample.view.features.news.ui.NewsActivity
import com.newssample.view.features.news.ui.NewsDetailActivity
import javax.inject.Inject
import javax.inject.Singleton


@Singleton
class Navigator
@Inject constructor(private val authenticator: Authenticator) {

    private fun showLogin(context: Context) =
        context.startActivity(LoginActivity.callingIntent(context))

    fun showMain(context: Context) {
        when (authenticator.userLoggedIn()) {
            true -> showNews(context)
            false -> showLogin(context)
        }
    }

    private fun showNews(context: Context) =
        context.startActivity(NewsActivity.callingIntent(context))

//    fun showMovieDetails(activity: FragmentActivity, movie: MovieView, navigationExtras: Extras) {
//        val intent = MovieDetailsActivity.callingIntent(activity, movie)
//        val sharedView = navigationExtras.transitionSharedElement as ImageView
//        val activityOptions = ActivityOptionsCompat
//            .makeSceneTransitionAnimation(activity, sharedView, sharedView.transitionName)
//        activity.startActivity(intent, activityOptions.toBundle())
//    }


    fun showNewsDetails(activity: FragmentActivity, news: PopularArticle, navigationExtras: Extras) {
        val intent = NewsDetailActivity.callingIntent(activity, news)
//        val sharedView = navigationExtras.transitionSharedElement as ImageView
//        val activityOptions = ActivityOptionsCompat
//            .makeSceneTransitionAnimation(activity, sharedView, sharedView.transitionName)
//        activity.startActivity(intent, activityOptions.toBundle())
        activity.startActivity(intent)
    }


    class Extras(val transitionSharedElement: View)
}


