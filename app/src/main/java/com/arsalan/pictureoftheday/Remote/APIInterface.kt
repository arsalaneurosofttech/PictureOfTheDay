package com.arsalan.pictureoftheday.Remote

import com.arsalan.pictureoftheday.Model.PictureModel
import org.json.JSONObject
import retrofit2.http.POST
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

public interface APIInterface {

    @GET("planetary/apod")
    fun getPicture(
        @Query("api_key") s: String?
    ): Call<PictureModel>

}