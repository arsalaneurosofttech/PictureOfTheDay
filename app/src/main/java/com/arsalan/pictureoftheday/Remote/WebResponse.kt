package com.arsalan.pictureoftheday.Remote

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class WebResponse<T> {
    @SerializedName("message")
    @Expose
    private var message: String? = null

    @SerializedName("data")
    @Expose
    private var data: T? = null

    @SerializedName("success")
    @Expose
    private var success: Boolean? = null

}