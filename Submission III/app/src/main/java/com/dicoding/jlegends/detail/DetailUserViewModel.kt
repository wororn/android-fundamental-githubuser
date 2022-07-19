package com.dicoding.jlegends.detail

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.dicoding.jlegends.favorite.FavoriteUser
import com.dicoding.jlegends.favorite.FavoriteUserDao
import com.dicoding.jlegends.favorite.UserDatabase
import com.dicoding.jlegends.retrofit.RetrofitClient
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class DetailUserViewModel(application: Application) : AndroidViewModel(application) {
    val user = MutableLiveData<DetailUserResponse>()
    private var userDao: FavoriteUserDao?
    private var userDb : UserDatabase? = UserDatabase.getDatabase(application)

    init{
        userDao = userDb?.favoriteUserDao()
    }

    fun setUserDetail(username: String) {
        RetrofitClient.apiInstance
            .detail(username)
            .enqueue(object : Callback<DetailUserResponse> {
                override fun onResponse(
                    call: Call<DetailUserResponse>,
                    response: Response<DetailUserResponse>
                ) {
                    if (response.isSuccessful) {
                        user.postValue(response.body())
                    }
                }

                override fun onFailure(call: Call<DetailUserResponse>, t: Throwable) {
                    Log.d("Error", t.message.toString())
                }

            })
    }

    fun getUserDetail(): LiveData<DetailUserResponse> {
        return user
    }
    fun addToFavorite(username: String,id:Int,avatarUrl:String){
        CoroutineScope(Dispatchers.IO).launch {
            val user = FavoriteUser(
                username,
                id,
                avatarUrl
            )
            userDao?.addToFavorite(user)
        }
    }
    suspend fun checkUser(id:Int)=userDao?.checkUser(id)
    fun removeFromFavorite(id:Int){
           CoroutineScope(Dispatchers.IO).launch {
               userDao?.removeFromFavorite(id)
           }
    }
}