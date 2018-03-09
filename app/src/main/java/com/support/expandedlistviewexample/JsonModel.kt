package com.support.expandedlistviewexample

/**
 * Created by admin on 3/9/2018.
 */

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class JsonModel {

    @SerializedName("one")
    @Expose
    var one: List<String>? = null
    @SerializedName("two")
    @Expose
    var two: List<String>? = null
    @SerializedName("three")
    @Expose
    var three: List<String>? = null
    @SerializedName("four")
    @Expose
    var four: List<String>? = null
    @SerializedName("five")
    @Expose
    var five: List<String>? = null

}


