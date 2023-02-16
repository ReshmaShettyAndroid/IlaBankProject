package com.example.ilabankproject.viewModel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.ilabankproject.R
import com.example.ilabankproject.model.ImageDataDto

class SlideViewModel : ViewModel() {
    var imageModelList = MutableLiveData<ArrayList<ImageDataDto>>()
    var dataList = ArrayList<ImageDataDto>()

    fun prepareImageList() :MutableLiveData<ArrayList<ImageDataDto>>{
        dataList.add(
            ImageDataDto(
                1,
                R.drawable.image1,
                mutableListOf("Rose 1.1","Rose 1.2","Rose 1.3","Rose 1.4","Rose 1.5","Rose 1.6","Rose 1.7","Rose 1.8","Rose 1.9","Rose 1.10","Rose 1.11","Rose 1.12","Rose 1.13")
            )
        )
        dataList.add(
            ImageDataDto(
                2,
                R.drawable.image2,
                mutableListOf("Image 2.1", "Image 2.2", "Image 2.3", "Image 2.4","Image 2.5","Image 2.6","Image 2.7","Image 2.8","Image 2.9","Image 2.10", "Image 2.11", "Fox","Leopard","Elephant","Horse","Monkey")
            )
        )
        dataList.add(
            ImageDataDto(
                3,
                R.drawable.image3,
                mutableListOf("Flower 1.1","Flower 1.2","Flower 1.3","Flower 1.4","Flower 1.5","Flower 1.6","Flower 1.7","Flower 1.8","Flower 1.9","Flower 1.10","Flower 1.11","Flower 1.12","Flower 1.13")
            )
        )
        if (imageModelList.value?.size ?: 0 == 0)
            imageModelList.value = dataList
        return imageModelList
    }
}

