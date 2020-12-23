package com.example.newsapidemo.network
import android.content.Context
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.toolbox.JsonObjectRequest
import com.example.newsapidemo.MainActivity
import com.example.newsapidemo.db.RealmDBUtilClass
import com.example.newsapidemo.model.ArticleObject
import com.google.gson.Gson
import org.json.JSONException
class PresenterClass(view: MainActivity) : NetworkInterface.PresenterToModel {

    private var view: NetworkInterface.PresenterToView? = null

    init {
        this.view = view
    }

    override fun loadData(context: Context,url:String) {
        getNewsData(context,url)
    }

    @Throws(JSONException::class)
    private fun getNewsData(context: Context,url: String) {
        val realm = RealmDBUtilClass()
        realm.deleteData()
        val request = JsonObjectRequest(
            Request.Method.GET, url, null,
            Response.Listener { response ->
                val jsonArray = response.getJSONArray("articles")
                for (i in 0 until jsonArray.length()) {
                    val jsonObjective = jsonArray.getJSONObject(i)
                    val gson = Gson()
                    val articleObject: ArticleObject? = gson.fromJson(
                        jsonObjective.toString(),
                        ArticleObject::class.java
                    )
                    realm.createRealmObject(articleObject)
                }
                view?.onSuccess(realm)
            },
            Response.ErrorListener {
                view?.onFailed()
            }
        )
        MyVolleyNetwork.getInstance(context).addToRequestQueue(request)
    }
}