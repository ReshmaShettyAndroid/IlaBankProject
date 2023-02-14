package com.example.ilabankproject.viewModel

import android.app.Application
import android.content.Context
import androidx.lifecycle.AndroidViewModel
import com.example.ilabankproject.model.ImageDataDto

class SlideViewModel(application: Application) : AndroidViewModel(application) {
    private val context: Context;
    private var imageModelList: MutableList<ImageDataDto>? = mutableListOf<ImageDataDto>()

    init {
        context = application.applicationContext
    }

    fun prepareImageList(): MutableList<ImageDataDto>? {
        lateinit var imageDataList: ImageDataDto
        var url = "https://picsum.photos/200/300?random=3"
        for (i in 1..3) {
            val labels: MutableList<String>? = mutableListOf<String>()
            for (j in 1..20) {
                labels?.add("Labels:-" + i + "." + j)
            }
            imageDataList = ImageDataDto(i, url + i, labels)
            imageModelList?.add(imageDataList)
        }
        return imageModelList;
    }
}

