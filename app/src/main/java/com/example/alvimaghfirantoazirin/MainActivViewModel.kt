package com.example.alvimaghfirantoazirin

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.loopj.android.http.AsyncHttpClient
import com.loopj.android.http.AsyncHttpResponseHandler
import cz.msebera.android.httpclient.Header
import org.json.JSONArray

class MainActivViewModel: ViewModel() {
    val listData = MutableLiveData<ArrayList<Entity>>()

    fun fetchData(){
        val listDataItem = ArrayList<Entity>()

        val url = "https://jsonplaceholder.typicode.com/todos"

        val client = AsyncHttpClient()
        client.get(url, object : AsyncHttpResponseHandler(){
            override fun onSuccess(
                statusCode: Int,
                headers: Array<out Header>,
                responseBody: ByteArray
            ) {
                try {
                    val result = String(responseBody)
                    val response = JSONArray(result)
                    for (i in 0 until response.length()){
                        val data = response.getJSONObject(i)
                        val entity = Entity(data.getInt("userId"),
                        data.getInt("id"),
                        data.getString("title"),
                        data.getString("completed"))

                        listDataItem.add(entity)
                    }
                    listData.postValue(listDataItem)
                } catch (e: Exception){
                    e.printStackTrace()
                }
            }

            override fun onFailure(
                statusCode: Int,
                headers: Array<out Header>?,
                responseBody: ByteArray?,
                error: Throwable
            ) {
                error.printStackTrace()
            }

        })
    }

    fun getData(): LiveData<ArrayList<Entity>> {
        return listData
    }
}