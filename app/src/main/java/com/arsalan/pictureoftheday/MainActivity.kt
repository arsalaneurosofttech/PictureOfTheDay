package com.arsalan.pictureoftheday

import android.R.attr.bitmap
import android.app.WallpaperManager
import android.content.res.Resources
import android.graphics.Bitmap
import android.graphics.Point
import android.graphics.Rect
import android.os.Build
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.arsalan.pictureoftheday.Model.PictureModel
import com.arsalan.pictureoftheday.Remote.APIClient
import com.arsalan.pictureoftheday.Remote.APIInterface
import com.arsalan.pictureoftheday.Utils.Constants
import com.arsalan.pictureoftheday.databinding.ActivityMainBinding
import com.bumptech.glide.Glide
import com.bumptech.glide.request.target.SimpleTarget
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.io.IOException


class MainActivity : AppCompatActivity() {
    lateinit var binding: ActivityMainBinding
    var picture = ""
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        getPicture()

        binding.wallpaper.setOnClickListener {
            Glide.with(this)
                .asBitmap()
                .load(picture)
                .into(object : SimpleTarget<Bitmap?>() {
                    override fun onResourceReady(
                        resource: Bitmap,
                        transition: com.bumptech.glide.request.transition.Transition<in Bitmap?>?
                    ) {
                        try {
                            val wallpaperManager = WallpaperManager.getInstance(this@MainActivity)
                            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                                val wallpaperHeight: Int =
                                    Resources.getSystem().displayMetrics.heightPixels
                                val wallpaperWidth: Int =
                                    Resources.getSystem().displayMetrics.widthPixels
                                val start = Point(0, 0)
                                val end = Point(resource.width, resource.height)
                                if (resource.width > wallpaperWidth) {
                                    start.x = (resource.width - wallpaperWidth) / 2
                                    end.x = start.x + wallpaperWidth
                                }
                                if (resource.height > wallpaperHeight) {
                                    start.y = (resource.height - wallpaperHeight) / 2
                                    end.y = start.y + wallpaperHeight
                                }
                                wallpaperManager.setBitmap(
                                    resource,
                                    Rect(start.x, start.y, end.x, end.y),
                                    false
                                )
                            } else {
                                wallpaperManager.setBitmap(resource)
                            }
                        } catch (e: IOException) {
                            e.printStackTrace()
                        }
                    }
                })
        }
    }

    private fun getPicture() {
        var apiClient = APIClient()
        var apiservice: APIInterface? = apiClient.getClient()?.create(APIInterface::class.java)

        var call: Call<PictureModel>? = apiservice?.getPicture(Constants.key)
        call?.enqueue(object : Callback<PictureModel> {
            override fun onResponse(call: Call<PictureModel>, response: Response<PictureModel>) {
                var jsonObject = response.body()
                var string = jsonObject?.url
                Glide.with(this@MainActivity).load(string).into(binding.picture)
                picture = jsonObject?.url.toString()
                binding.description.text = jsonObject?.explanation
                binding.copyright.text = "${jsonObject?.copyright} : ${jsonObject?.date}"


            }

            override fun onFailure(call: Call<PictureModel>, t: Throwable) {
                Toast.makeText(this@MainActivity, "Not Hello", Toast.LENGTH_SHORT).show()
            }
        })
    }
}