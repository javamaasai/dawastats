package com.dawastatistics.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.dawastatistics.model.PostInfo
import com.dawastatistics.repository.RetrofitRepository

class RetroViewModel(retrofitRepository: RetrofitRepository): ViewModel() {

    var retrofitRepository:RetrofitRepository = retrofitRepository
    var postInfoLiveData: LiveData<List<PostInfo>> = MutableLiveData()

    init {
        fetchPostInfoFromRepository()
    }

    fun fetchPostInfoFromRepository(){
//        Timer().scheduleAtFixedRate(object : TimerTask() {
//            override fun run() {
//                Log.e("NIlu_TAG","Hello World")
//            }
//        },2000,2)
        postInfoLiveData =  retrofitRepository.fetchPostInfoList()
    }
}