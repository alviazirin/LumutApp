package com.example.alvimaghfirantoazirin

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.loopj.android.http.AsyncHttpClient
import com.loopj.android.http.AsyncHttpResponseHandler
import cz.msebera.android.httpclient.Header
import org.json.JSONObject

class DetailViewModel : ViewModel() {

    val dataLive = MutableLiveData<Entity>()

    fun fetchDetail(id: Int) {
        val url = "https://jsonplaceholder.typicode.com/todos/${id}"
        val client = AsyncHttpClient()
        client.get(url, object : AsyncHttpResponseHandler() {
            override fun onSuccess(
                statusCode: Int,
                headers: Array<out Header>?,
                responseBody: ByteArray
            ) {
                try {
                    val result = String(responseBody)
                    val response = JSONObject(result)
                    val data = Entity(
                        response.getInt("userId"),
                        response.getInt("id"),
                        response.getString("title"),
                        response.getString("completed")
                    )
                    dataLive.postValue(data)
                } catch (e: Exception){
                    e.printStackTrace()
                }
            }

            override fun onFailure(
                statusCode: Int,
                headers: Array<out Header>?,
                responseBody: ByteArray?,
                error: Throwable?
            ) {
                error?.printStackTrace()
            }

        })
    }

    fun getData(): MutableLiveData<Entity> {
        return dataLive
    }
}