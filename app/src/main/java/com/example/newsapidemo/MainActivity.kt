package com.example.newsapidemo

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.newsapidemo.adapter.CardView
import com.example.newsapidemo.adapter.RecyclerAdapter
import com.example.newsapidemo.db.RealmDBUtilClass
import com.example.newsapidemo.model.RealmDataObject
import com.example.newsapidemo.network.MyVolleyNetwork
import com.example.newsapidemo.network.NetworkInterface
import com.example.newsapidemo.network.PresenterClass
import io.realm.Realm

class MainActivity : AppCompatActivity(), RecyclerAdapter.OnItemClickListener,
    NetworkInterface.PresenterToView {

    private var presenter: NetworkInterface.PresenterToModel? = null
    private lateinit var recyclerView: RecyclerView
    lateinit var editTextSearch: EditText
    lateinit var buttonSearch: Button


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val (editTextSearch, button_search) = initView()

        recyclerView = findViewById(R.id.my_recycler_view)
        recyclerView.layoutManager = LinearLayoutManager(this, RecyclerView.VERTICAL, false)

        button_search.setOnClickListener {
            Realm.init(this)
            MyVolleyNetwork.getInstance(this).requestQueue
            val editText: String = editTextSearch.text.toString()
            val url =
                "https://newsapi.org/v2/everything?q=${editText}&apiKey=97d0781f17c54bbe8c97d7872919bad3"
            presenter = PresenterClass(this)
            presenter?.loadData(this, url)
        }
    }

    private fun initView(): Pair<EditText, Button> {
        editTextSearch = findViewById(R.id.edit_text_search)
        buttonSearch = findViewById(R.id.button_search)
        return Pair(editTextSearch, buttonSearch)
    }

    override fun onItemClick(realmDataObject: RealmDataObject) {

        val intent = Intent(this, CardView::class.java)
        intent.putExtra("title", realmDataObject.title)
        intent.putExtra("author", realmDataObject.author)
        intent.putExtra("description", realmDataObject.description)
        intent.putExtra("urlImage",realmDataObject.urlToImage)
        startActivity(intent)
    }

    override fun onSuccess(realm: RealmDBUtilClass) {
        val adapter = RecyclerAdapter(realm.readData(), this)
        recyclerView.adapter = adapter
    }

    override fun onFailed() {
        Toast.makeText(this, "Error", Toast.LENGTH_LONG).show()
    }


}
