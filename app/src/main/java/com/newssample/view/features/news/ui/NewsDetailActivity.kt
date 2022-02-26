package com.newssample.view.features.news.ui

import android.content.Context
import android.content.Intent
import com.newssample.view.core.platform.BaseActivity
import com.newssample.view.features.news.model.PopularArticle

class NewsDetailActivity: BaseActivity() {

    companion object {
        private const val INTENT_EXTRA_PARAM_NEWS = "com.newssample.INTENT_PARAM_NEWS"

        fun callingIntent(context: Context, news: PopularArticle) =
            Intent(context, NewsDetailActivity::class.java).apply {
                putExtra(INTENT_EXTRA_PARAM_NEWS, news)
            }
    }

    override fun fragment() =
        NewsDetailsFragment.forNews(intent.getParcelableExtra(INTENT_EXTRA_PARAM_NEWS))
}
