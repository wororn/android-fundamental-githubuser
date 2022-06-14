package com.dicoding.jlegends.api


import com.dicoding.jlegends.BuildConfig
import com.dicoding.jlegends.detail.DetailUserResponse
import com.dicoding.jlegends.user.User
import com.dicoding.jlegends.user.UserResponse
import retrofit2.Call
import retrofit2.http.*

interface Api {
    @GET("search/users")
    @Headers("Authorization: ${BuildConfig.KEY}")
    fun search(
        @Query("q") query: String
    ): Call<UserResponse>

    @GET("users/{username}")
    @Headers("Authorization: token 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")
    fun detail(
        @Path("username") username: String
    ): Call<DetailUserResponse>

    @GET("users/{username}/followers")
    @Headers("Authorization: token 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")
    fun followers(
        @Path("username") username: String
    ): Call<ArrayList<User>>

    @GET("users/{username}/following")
    @Headers("Authorization: token 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")
    fun following(
        @Path("username") username: String
    ): Call<ArrayList<User>>
}