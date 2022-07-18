package com.dicoding.jlegends.detail

import com.google.gson.annotations.SerializedName

data class DetailUserResponse(
    val login: String,
    val id: Int,
    @field:SerializedName("avatar_url")
    val avatar_url: String,
    val name: String,
    val following: Int,
    val followers: Int,
    val company: String,
    val location: String,
    val public_repos: Int
)
