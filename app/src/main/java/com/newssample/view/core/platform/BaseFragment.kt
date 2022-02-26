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
package com.newssample.view.core.platform

import android.app.Dialog
import android.os.Bundle
import androidx.annotation.StringRes
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import com.newssample.view.R.color
import com.newssample.view.core.extension.appContext
import com.newssample.view.core.extension.viewContainer
import com.google.android.material.snackbar.Snackbar
import com.newssample.view.features.news.util.Utility
import dagger.hilt.android.AndroidEntryPoint

/**
 * Base Fragment class with helper methods for handling views and back button events.
 *
 * @see Fragment
 */
@AndroidEntryPoint
abstract class BaseFragment : Fragment() {

    lateinit var loadingDialog : Dialog

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        loadingDialog = Utility.createLoadingDialog(requireContext())

    }


    open fun onBackPressed() {}

    internal fun showProgress() =  loadingDialog.show()

    internal fun hideProgress() = loadingDialog.dismiss()

    internal fun notify(@StringRes message: Int) =
        Snackbar.make(viewContainer, message, Snackbar.LENGTH_SHORT).show()

    internal fun notifyWithAction(
        @StringRes message: Int,
        @StringRes actionText: Int,
        action: () -> Any
    ) {
        val snackBar = Snackbar.make(viewContainer, message, Snackbar.LENGTH_INDEFINITE)
        snackBar.setAction(actionText) { _ -> action.invoke() }
        snackBar.setActionTextColor(ContextCompat.getColor(appContext, color.colorTextPrimary))
        snackBar.show()
    }
}
