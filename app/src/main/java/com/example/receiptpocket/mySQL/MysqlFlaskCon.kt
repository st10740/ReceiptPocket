package com.example.receiptpocket.mySQL

import android.util.Log
import com.google.gson.Gson
import okhttp3.*
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.RequestBody.Companion.toRequestBody
import java.io.IOException

class MysqlFlaskCon {
    private val ip = "http://192.168.2.26"  //"http://192.168.43.71"
    private val port = "5000"

    fun getAccount(id_: String): MutableList<String>{
        val accountList = mutableListOf<String>()

        val url = "$ip:$port/account/get?id=$id_"

        val okHttpClient = OkHttpClient().newBuilder().build()
        val request: Request = Request.Builder().url(url).get().build()
        val call = okHttpClient.newCall(request)

        try {
            val response = call.execute()
            val resStr = response.body?.string()
            Log.d("Http", "onResponse: $resStr")

            val user = Gson().fromJson(resStr, UserModel::class.java)
            accountList.add(0, user.id)
            accountList.add(1, user.name)
            accountList.add(2, user.password)

        } catch (e: IOException){
            Log.d("Http", "onFailure: $e")
        }


        return accountList
    }

    fun insertAccount(id: String, name: String, password: String){
        val url = "$ip:$port/account/insert"

        val user = UserModel(id, name, password)
        val userJson = Gson().toJson(user)
        val mediaType = "application/json; charset=utf-8".toMediaType()
        val requestBody = userJson.toString().toRequestBody(mediaType)

        val okHttpClient = OkHttpClient().newBuilder().build()
        val request: Request = Request.Builder().url(url).post(requestBody).build()
        val call = okHttpClient.newCall(request)

        try {
            val response = call.execute()
            Log.d("Http", "onResponse: ${response.body?.string()}")

        } catch (e: IOException){
            Log.d("Http", "onFailure: $e")
        }

    }

    fun getReceipts(id_: String): List<ReceiptModel>{
        var receiptList = listOf<ReceiptModel>()

        val url = "$ip:$port/receipts/get?sid=$id_"

        val okHttpClient = OkHttpClient().newBuilder().build()
        val request: Request = Request.Builder().url(url).get().build()
        val call = okHttpClient.newCall(request)

        try {
            val response = call.execute()
            val resStr = response.body?.string()
            Log.d("Http", "onResponse: $resStr")

            receiptList = Gson().fromJson(resStr, Array<ReceiptModel>::class.java).toList()


        } catch (e: IOException){
            Log.d("Http", "onFailure: $e")
        }


        return receiptList
    }

    fun insertReceipts(receiptsList: List<ReceiptModel>){
        val url = "$ip:$port/receipts/insert"

        val receiptsJson = Gson().toJson(receiptsList)
        val mediaType = "application/json; charset=utf-8".toMediaType()
        val requestBody = receiptsJson.toString().toRequestBody(mediaType)

        val okHttpClient = OkHttpClient().newBuilder().build()
        val request: Request = Request.Builder().url(url).post(requestBody).build()
        val call = okHttpClient.newCall(request)

        try {
            val response = call.execute()
            val resStr = response.body?.string()
            Log.d("Http", "onResponse: $resStr")

        } catch (e: IOException){
            Log.d("Http", "onFailure: $e")
        }

    }

    fun deleteReceiptsById(id_: String){
        val url = "$ip:$port/receipts/delete?sid=$id_"

        val reqbody = "".toRequestBody(null)

        val okHttpClient = OkHttpClient().newBuilder().build()
        val request: Request = Request.Builder().url(url).post(reqbody).build()
        val call = okHttpClient.newCall(request)

        try {
            val response = call.execute()
            val resStr = response.body?.string()
            Log.d("Http", "onResponse: $resStr")

        } catch (e: IOException){
            Log.d("Http", "onFailure: $e")
        }

    }
}
