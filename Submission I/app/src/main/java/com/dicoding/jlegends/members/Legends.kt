package com.dicoding.jlegends.members

import android.os.Parcel
import android.os.Parcelable

data class Legends(
    var name: String? = "",
    var area: String = "",
    var photo: Int = 0,
    var overview: String = "",
    var detail: String = ""
) : Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readString().toString(),
        parcel.readString().toString(),
        parcel.readInt(),
        parcel.readString().toString(),
        parcel.readString().toString()
    ) {
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(name)
        parcel.writeString(area)
        parcel.writeInt(photo)
        parcel.writeString(overview)
        parcel.writeString(detail)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<Legends> {
        override fun createFromParcel(parcel: Parcel): Legends {
            return Legends(parcel)
        }

        override fun newArray(size: Int): Array<Legends?> {
            return arrayOfNulls(size)
        }
    }
}