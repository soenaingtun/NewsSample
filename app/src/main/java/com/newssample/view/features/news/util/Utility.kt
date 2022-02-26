package com.newssample.view.features.news.util

import android.app.Dialog
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.widget.LinearLayout
import java.text.ParseException
import java.text.SimpleDateFormat
import java.util.*
import com.newssample.view.R

object Utility {

    fun formatDate(date: String): String {
        val inputFormat = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'", Locale.UK)
        val outputFormat = SimpleDateFormat("yyyy/MM/dd ", Locale.UK)
        try {
            return outputFormat.format(inputFormat.parse(date) ?: "")
        } catch (e: ParseException) {
            e.printStackTrace()
        }
        return ""
    }


    fun createLoadingDialog(context: Context): Dialog {

        // First get the entire View
        val view: View = LayoutInflater.from(context).inflate(
            R.layout.loading_dialog, null
        )
        // Get the entire layout
        val layout = view
            .findViewById(R.id.dialog_view) as LinearLayout
        // Create a custom style Dialog
        val loadingDialog = Dialog(context, R.style.loading_dialog)
        // Setting the return key is invalid
        loadingDialog.setCancelable(false)
        loadingDialog.setContentView(
            layout, LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.MATCH_PARENT
            )
        )
        return loadingDialog
    }

}