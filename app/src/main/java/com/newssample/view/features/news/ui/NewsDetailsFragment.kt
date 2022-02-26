package com.newssample.view.features.news.ui

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import com.bumptech.glide.Glide
import com.newssample.view.core.extension.isVisible
import com.newssample.view.core.platform.BaseFragment
import com.newssample.view.databinding.FragmentNewsDetailBinding
import com.newssample.view.features.news.model.PopularArticle
import com.newssample.view.features.news.util.Utility
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class NewsDetailsFragment : BaseFragment() {

    private var _binding: FragmentNewsDetailBinding? = null
    private val binding get() = _binding!!


    companion object {
        private const val PARAM_NEWS = "param_news"

        fun forNews(news: PopularArticle?) = NewsDetailsFragment().apply {
            arguments = bundleOf(PARAM_NEWS to news)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        _binding = FragmentNewsDetailBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initializeView()
    }

    private fun initializeView(){

        val args = (arguments?.get(NewsDetailsFragment.PARAM_NEWS) as PopularArticle)
        //load image using glide
        Glide.with(binding.imgNews)
            .load(args.urlToImage)
            .into(binding.imgNews)

        //display date
        binding.txtDate.text = args.publishedAt?.let { Utility.formatDate(it) }

        //display title
        binding.txtTitle.text = args.title

        //display source
        binding.txtSource.text = args.author

        //display article
        binding.txtArticle.text = args.content

        binding.btnViewFullArticle.setOnClickListener {

            val openURL = Intent(android.content.Intent.ACTION_VIEW)
            openURL.data = Uri.parse(args.url)
            startActivity(openURL)

        }
    }

    override fun onBackPressed() {
 //       movieDetailsAnimator.fadeInvisible(binding.scrollView, binding.movieDetails)
//        if (binding.moviePlay.isVisible())
//            movieDetailsAnimator.scaleDownView(binding.moviePlay)
//        else
//            movieDetailsAnimator.cancelTransition(binding.moviePoster)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}