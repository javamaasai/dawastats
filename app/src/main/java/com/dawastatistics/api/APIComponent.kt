package com.dawastatistics.api

import com.dawastatistics.AppModule
import com.dawastatistics.repository.RetrofitRepository
import com.dawastatistics.ui.RetroFragment
import com.dawastatistics.viewmodel.RetroViewModel
import com.dawastatistics.viewmodel.RetroViewModelFactory
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [AppModule::class,APIModule::class])
interface APIComponent {
    fun inject(retrofitRepository: RetrofitRepository)
    fun inject(retroViewModel: RetroViewModel)
    fun inject(retroFragment: RetroFragment)
    fun inject(retroViewModelFactory:RetroViewModelFactory)
}