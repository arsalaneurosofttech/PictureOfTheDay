package com.arsalan.pictureoftheday.Model

import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class PictureModel(
    @SerializedName("copyright"       ) var copyright      : String? = null,
    @SerializedName("date"            ) var date           : String? = null,
    @SerializedName("explanation"     ) var explanation    : String? = null,
    @SerializedName("hdurl"           ) var hdurl          : String? = null,
    @SerializedName("media_type"      ) var mediaType      : String? = null,
    @SerializedName("service_version" ) var serviceVersion : String? = null,
    @SerializedName("title"           ) var title          : String? = null,
    @SerializedName("url"             ) var url            : String? = null
) : Serializable
