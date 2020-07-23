package com.dawastatistics

import dagger.Module
import dagger.Provides

@Module
class AppModule constructor(drugApplication: DrugApplication){
    var drugApplication:DrugApplication = drugApplication

    @Provides
    fun provideMyRetroApplication():DrugApplication{
        return drugApplication
    }
}