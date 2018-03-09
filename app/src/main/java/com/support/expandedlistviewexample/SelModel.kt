package com.support.expandedlistviewexample

import android.os.Parcel
import android.os.Parcelable

/**
 * Created by admin on 3/9/2018.
 */

class SelModel() : Parcelable {
     var parentpos:Int? = null
     var childpos:Int? = null
     var item:String? = null

    constructor(parcel: Parcel) : this() {
        parentpos = parcel.readValue(Int::class.java.classLoader) as? Int
        childpos = parcel.readValue(Int::class.java.classLoader) as? Int
        item = parcel.readString()
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeValue(parentpos)
        parcel.writeValue(childpos)
        parcel.writeString(item)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<SelModel> {
        override fun createFromParcel(parcel: Parcel): SelModel {
            return SelModel(parcel)
        }

        override fun newArray(size: Int): Array<SelModel?> {
            return arrayOfNulls(size)
        }
    }


}
