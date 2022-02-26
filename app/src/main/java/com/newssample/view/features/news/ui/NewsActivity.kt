package com.newssample.view.features.news.ui

import android.content.Context
import android.content.Intent
import com.newssample.view.core.platform.BaseActivity

class NewsActivity : BaseActivity() {

    companion object {
        fun callingIntent(context: Context) = Intent(context, NewsActivity::class.java)
    }
    override fun fragment() = NewsFragment()
}
