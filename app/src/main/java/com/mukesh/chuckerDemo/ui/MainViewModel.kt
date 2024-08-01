package com.mukesh.chuckerDemo.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mukesh.chuckerDemo.model.Comment
import com.mukesh.chuckerDemo.model.Posts
import com.mukesh.chuckerDemo.network.ApiCall
import com.mukesh.chuckerDemo.network.NetworkStatus
import com.mukesh.chuckerDemo.utils.setData
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.launch
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.RequestBody.Companion.toRequestBody
import org.json.JSONObject

class MainViewModel : ViewModel() {

    private val _allPosts by lazy { MutableLiveData<NetworkStatus<List<Posts>?>>() }
    val allPosts: LiveData<NetworkStatus<List<Posts>?>> = _allPosts


    fun getAllPosts() = viewModelScope.launch {
        flow {
            emit(ApiCall.makeCall().getAllPosts())
        }.flowOn(Dispatchers.IO).setData(_allPosts)
    }


    private val _postDetail by lazy { MutableLiveData<NetworkStatus<Posts?>>() }
    val postDetail: LiveData<NetworkStatus<Posts?>> = _postDetail

    fun getPostDetail(id: String) = viewModelScope.launch {
        flow {
            emit(ApiCall.makeCall().getPostById(postId = id))
        }.flowOn(Dispatchers.IO).setData(_postDetail)
    }


    private val _commentsData by lazy { MutableLiveData<NetworkStatus<List<Comment>?>>() }
    val commentData: LiveData<NetworkStatus<List<Comment>?>> = _commentsData

    fun getAllComments(id: String) = viewModelScope.launch {
        flow {
            emit(ApiCall.makeCall().getComments(headerMap = HashMap<String, String>().apply {
                if (id == "5") put("channelNameMap", "Dev No Limit- Retrofit")
                else put("headerMapDataKey", "Retrofit Playlist")
            }, queryMap = HashMap<String, String>().apply {
                put("postId", id)
                if (id == "1") put("name", "Dev No Limit")
            }))
        }.flowOn(Dispatchers.IO).setData(_commentsData)
    }



    private val _addPost by lazy { MutableLiveData<NetworkStatus<Any?>>() }
    val addPost: LiveData<NetworkStatus<Any?>> = _addPost

    fun addPost() = viewModelScope.launch {
        flow {
            emit(ApiCall.makeCall().addPost(
                requestBody = JSONObject().apply {
                    put("title", "Demo")
                    put("body", "Retrofit Playlist")
                    put("userId", 1)
                }.toString().toRequestBody("application/json".toMediaTypeOrNull())
            ))
        }.flowOn(Dispatchers.IO).setData(_addPost)
    }


    fun addPostWithFormData() = viewModelScope.launch {
        flow {
            emit(ApiCall.makeCall().addPostWithFormData(
                title = "Demo",
                body = "Retrofit Playlist",
                userId = 1
            ))
        }.flowOn(Dispatchers.IO).setData(_addPost)
    }


    fun putPostMethod() = viewModelScope.launch {
        flow {
            emit(ApiCall.makeCall().putPostMethod(
                postId = "1",
                requestBody = JSONObject().apply {
                    put("id", 1)
                    put("title", "foo")
                    put("body", "bar")
                    put("userId", 1)
                }.toString().toRequestBody("application/json".toMediaTypeOrNull())
            ))
        }.flowOn(Dispatchers.IO).setData(_addPost)
    }



    fun patchMethodPost() = viewModelScope.launch {
        flow {
            emit(ApiCall.makeCall().patchPost(
                postId = "1",
                requestBody = JSONObject().apply {
                    put("title", "foo")
                }.toString().toRequestBody("application/json".toMediaTypeOrNull())
            ))
        }.flowOn(Dispatchers.IO).setData(_addPost)
    }


    fun deletePost() = viewModelScope.launch {
        flow {
            emit(ApiCall.makeCall().deletePost(postId = "1"))
        }.flowOn(Dispatchers.IO).setData(_addPost)
    }

}