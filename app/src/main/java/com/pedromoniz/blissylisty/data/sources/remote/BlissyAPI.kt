package com.pedromoniz.blissylisty.data.sources.remote

import com.pedromoniz.blissylisty.data.sources.remote.models.HealthStatusRemoteModel
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.POST

interface BlissyAPI {
    companion object {
        private const val HEALTH = "health"
    }

    @Headers("Content-Type: application/json")
    @GET(HEALTH)
    suspend fun checkHealthStatus(): HealthStatusRemoteModel
}