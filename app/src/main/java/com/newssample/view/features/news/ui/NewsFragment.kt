package com.newssample.view.features.news.ui

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.StringRes
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.newssample.view.R
import com.newssample.view.core.exception.Failure
import com.newssample.view.core.extension.failure
import com.newssample.view.core.extension.invisible
import com.newssample.view.core.extension.observe
import com.newssample.view.core.extension.visible
import com.newssample.view.core.navigation.Navigator
import com.newssample.view.core.platform.BaseFragment
import com.newssample.view.databinding.FragmentNewsBinding
import com.newssample.view.features.news.model.PopularNews
import com.newssample.view.features.news.util.NewsFailure
import com.newssample.view.features.news.util.Urls
import com.newssample.view.features.news.viewmodel.NewsViewModel
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class NewsFragment : BaseFragment() {

    @Inject
    lateinit var navigator: Navigator
    @Inject
    lateinit var newsAdapter: NewsAdapter

    private val newsViewModel: NewsViewModel by viewModels()

    private var _binding: FragmentNewsBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        with(newsViewModel) {
            observe(popularNews, ::renderNewsList)
            failure(failure, ::handleFailure)
        }
    }


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentNewsBinding.inflate(inflater, container, false)
        return binding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initializeView()
        loadNewsList()
    }


    private fun initializeView() {
        binding.newsList.layoutManager = LinearLayoutManager(requireContext())
        binding.newsList.adapter = newsAdapter
        newsAdapter.clickListener = { news ->
            navigator.showNewsDetails(requireActivity(), news)
        }
    }

    private fun loadNewsList() {

        binding.emptyView.invisible()
        binding.newsList.visible()
        showProgress()
        newsViewModel.getPopularNews("tesla",Urls.API_KEY,10)
    }

    private fun renderNewsList(newsData: PopularNews?) {
        hideProgress()
        val newsList = newsData!!.popularArticles
        newsAdapter.collection = newsList.orEmpty()

    }

    private fun handleFailure(failure: Failure?) {
        when (failure) {
            is Failure.NetworkConnection -> renderFailure(R.string.failure_network_connection)
            is Failure.ServerError -> renderFailure(R.string.failure_server_error)
            is NewsFailure.ListNotAvailable -> renderFailure(R.string.failure_news_list_unavailable)
            else -> renderFailure(R.string.failure_server_error)
        }
    }

    private fun renderFailure(@StringRes message: Int) {
        binding.newsList.invisible()
        binding.emptyView.visible()
        hideProgress()
        notifyWithAction(message, R.string.action_refresh, ::loadNewsList)
    }


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}
