package com.pedromoniz.blissylisty.data.sources.remote.models

import com.squareup.moshi.Json


data class StatusRemoteModel (
    @field:Json(name = "status") val status: String
){
    fun toBoolean()= status == "OK"

    companion object {
        fun empty() = StatusRemoteModel("")
    }
}
