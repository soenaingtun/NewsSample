package com.newssample.view.features.news.model

import android.os.Parcel
import com.newssample.view.core.platform.KParcelable
import com.newssample.view.core.platform.parcelableCreator
import java.io.Serializable

data class PopularArticle(val id: Int,
                          val url: String?,
                          val author: String?,
                          val content: String?,
                          val description: String?,
                          val publishedAt: String?,
                         // val source: Source?,
                          val title: String?,
                          val urlToImage: String?): KParcelable {
    companion object {
        @JvmField
        val CREATOR = parcelableCreator(::PopularArticle)
    }

    constructor(parcel: Parcel) : this(
        parcel.readInt(),
        parcel.readString()!!,
        parcel.readString()!!,
        parcel.readString()!!,
        parcel.readString()!!,
        parcel.readString()!!,
        parcel.readString()!!,
        parcel.readString()
    )

    override fun writeToParcel(dest: Parcel, flags: Int) {
        with(dest) {
            writeInt(id)
            writeString(url)
            writeString(author)
            writeString(description)
            writeString(publishedAt)
            writeString(title)
            writeString(urlToImage)
        }
    }
}