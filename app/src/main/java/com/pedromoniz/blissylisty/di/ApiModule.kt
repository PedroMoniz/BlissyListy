package com.pedromoniz.blissylisty.di

import com.pedromoniz.blissylisty.data.sources.remote.BlissyAPI
import org.koin.dsl.module
import retrofit2.Retrofit

val apiModule = module {
    single(createdAtStart = false) { provideRetrofit()}
    single(createdAtStart = false) { get<Retrofit>().create(BlissyAPI::class.java) }
}

fun provideRetrofit(): Retrofit {
    return Retrofit.Builder()
        .baseUrl("https://private-bbbe9-blissrecruitmentapi.apiary-mock.com")
        .build()
}
