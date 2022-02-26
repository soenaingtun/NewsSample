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



    fun showNewsDetails(activity: FragmentActivity, news: PopularArticle) {
        val intent = NewsDetailActivity.callingIntent(activity, news)
        activity.startActivity(intent)
    }


}


