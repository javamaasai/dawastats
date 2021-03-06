package com.dawastatistics.repository

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.dawastatistics.DrugApplication
import com.dawastatistics.api.APIComponent
import com.dawastatistics.model.PostInfo
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import io.reactivex.schedulers.Timed
import retrofit2.Retrofit
import java.util.*
import java.util.concurrent.TimeUnit
import javax.inject.Inject
import kotlin.collections.ArrayList
import kotlin.concurrent.schedule

class RetrofitRepository {
    var postInfoMutableList: MutableLiveData<List<PostInfo>> = MutableLiveData()
    var postInfoList = ArrayList<PostInfo>()
    var myCompositeDisposable: CompositeDisposable? = null
    var t:TimerTask? =null
    @Inject
    lateinit var retrofit: Retrofit
    init {
        var apiComponent :APIComponent =  DrugApplication.apiComponent
        apiComponent.inject(this)
        myCompositeDisposable = CompositeDisposable()
        postInfoMutableList.value = postInfoList
    }

    fun fetchPostInfoList(): LiveData<List<PostInfo>> {
        var apiService:APIService = retrofit.create(APIService::class.java)

        myCompositeDisposable?.add(apiService.makeRxListRequest()
            .timeInterval(TimeUnit.SECONDS,Schedulers.newThread())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeOn(Schedulers.io())
            .subscribe(this::handleListResponse,this::handleError))

        return  postInfoMutableList
    }

    private fun handleSingleResponse(postInfo: PostInfo) {
        postInfoList.add(postInfo)
        postInfoMutableList.value = postInfoList
    }

    private fun handleListResponse(postInfoList: Timed<List<PostInfo>>) {
        t?.cancel()
        postInfoMutableList.value = postInfoList.value()
    }

    private fun handleError(error: Throwable) {

        Log.d("RetroRepository","Failed:::"+error.localizedMessage)

        t = Timer("SettingUp", false).schedule(3000) {
            fetchPostInfoList()
        }
    }
}