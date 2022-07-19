package com.dicoding.jlegends.main

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.dicoding.jlegends.retrofit.RetrofitClient
import com.dicoding.jlegends.user.User
import com.dicoding.jlegends.user.UserResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainViewModel : ViewModel() {

    val listUsers = MutableLiveData<ArrayList<User>>()

    fun setSearchUsers(query: String) {
        RetrofitClient.apiInstance
            .search(query)
            .enqueue(object : Callback<UserResponse> {
                override fun onResponse(
                    call: Call<UserResponse>,
                    response: Response<UserResponse>
                ) {
                    if (response.isSuccessful) {
                        listUsers.postValue(response.body()?.items)
                    }
                }

                override fun onFailure(call: Call<UserResponse>, t: Throwable) {
                    t.message?.let { Log.d("Error", it) }
                }

            })
    }

    fun getSearchUser(): LiveData<ArrayList<User>> {
        return listUsers
    }
}