package com.example.potatopaloozac.simpsoncodingchallengeproj.model

import android.os.Parcel
import android.os.Parcelable
import com.google.gson.annotations.SerializedName

data class RelatedTopicsItem(

    @field:SerializedName("Text")
    val text: String? = null,

    @field:SerializedName("Icon")
    val icon: Icon? = null,

    @field:SerializedName("FirstURL")
    val firstURL: String? = null,

    @field:SerializedName("Result")
    val result: String? = null
) : Parcelable {
    constructor(source: Parcel) : this(
        source.readString(),
        source.readParcelable<Icon>(Icon::class.java.classLoader),
        source.readString(),
        source.readString()
    )

    override fun describeContents() = 0

    override fun writeToParcel(dest: Parcel, flags: Int) = with(dest) {
        writeString(text)
        writeParcelable(icon, 0)
        writeString(firstURL)
        writeString(result)
    }

    companion object {
        @JvmField
        val CREATOR: Parcelable.Creator<RelatedTopicsItem> = object : Parcelable.Creator<RelatedTopicsItem> {
            override fun createFromParcel(source: Parcel): RelatedTopicsItem = RelatedTopicsItem(source)
            override fun newArray(size: Int): Array<RelatedTopicsItem?> = arrayOfNulls(size)
        }
    }
}