package com.dawastatistics

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import com.dawastatistics.model.PostInfo
import com.dawastatistics.repository.RetrofitRepository
import com.dawastatistics.viewmodel.RetroViewModel
import org.junit.Assert
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.junit.MockitoJUnitRunner
import org.mockito.Mock
import org.mockito.Mockito.`when`
import org.mockito.MockitoAnnotations

@RunWith(MockitoJUnitRunner::class)
class RetroViewModelTest {

    lateinit var retroViewModel: RetroViewModel

    @Mock
    lateinit var retrofitRepository: RetrofitRepository
    private lateinit var postInfoEmptyList: List<PostInfo>
    private val mockList: MutableList<PostInfo> = mutableListOf()
    private val mockLiveData: MutableLiveData<List<PostInfo>> = MutableLiveData()

    @get:Rule
    val rule = InstantTaskExecutorRule()

    @Before
    fun init() {
        MockitoAnnotations.initMocks(this);
        this.retroViewModel = RetroViewModel(this.retrofitRepository)

        mockData()
    }

    @Test
    fun fetchPostInfoFromRepositoryTest() {
        `when`(retrofitRepository.fetchPostInfoList()).thenReturn(mockLiveData)
        retroViewModel.postInfoLiveData = mockLiveData
        Assert.assertNotNull(retroViewModel.postInfoLiveData.value)
        Assert.assertTrue(retroViewModel.postInfoLiveData.value?.size == 5)
    }

    private fun mockData() {
        postInfoEmptyList = emptyList()

        mockList.add(PostInfo("1", "JAVAMAASAI", "THROMBOSIS", "Medicine for better rural health"))
        mockList.add(PostInfo("1", "JAVAMAASAI", "THROMBOSIS", "Medicine for better rural health"))
        mockList.add(PostInfo("1", "JAVAMAASAI", "THROMBOSIS", "Medicine for better rural health"))
        mockList.add(PostInfo("1", "JAVAMAASAI", "THROMBOSIS", "Medicine for better rural health"))
        mockList.add(PostInfo("1", "JAVAMAASAI", "THROMBOSIS", "Medicine for better rural health"))

        postInfoEmptyList = mockList.toList()
        mockLiveData.postValue(postInfoEmptyList)
    }
}