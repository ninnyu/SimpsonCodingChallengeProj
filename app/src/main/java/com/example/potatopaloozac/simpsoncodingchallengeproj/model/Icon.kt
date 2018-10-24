package com.example.potatopaloozac.simpsoncodingchallengeproj.model

import android.os.Parcel
import android.os.Parcelable
import com.google.gson.annotations.SerializedName

data class Icon(

	@field:SerializedName("Height")
	val height: String? = null,

	@field:SerializedName("Width")
	val width: String? = null,

	@field:SerializedName("URL")
	val uRL: String? = null
) : Parcelable {
	constructor(source: Parcel) : this(
		source.readString(),
		source.readString(),
		source.readString()
	)

	override fun describeContents() = 0

	override fun writeToParcel(dest: Parcel, flags: Int) = with(dest) {
		writeString(height)
		writeString(width)
		writeString(uRL)
	}

	companion object {
		@JvmField
		val CREATOR: Parcelable.Creator<Icon> = object : Parcelable.Creator<Icon> {
			override fun createFromParcel(source: Parcel): Icon = Icon(source)
			override fun newArray(size: Int): Array<Icon?> = arrayOfNulls(size)
		}
	}
}