package com.newssample.view.features.news.util

import com.newssample.view.core.exception.Failure

class NewsFailure {
    class ListNotAvailable : Failure.FeatureFailure()
    class NonExistentMovie : Failure.FeatureFailure()
}