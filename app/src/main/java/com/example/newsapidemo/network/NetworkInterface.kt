package com.example.newsapidemo.network

import android.content.Context
import com.example.newsapidemo.db.RealmDBUtilClass

class NetworkInterface {

    interface PresenterToView{
        fun onSuccess(realm : RealmDBUtilClass)
        fun onFailed()
    }


    interface PresenterToModel{
        fun loadData(context: Context,url:String)
    }
}