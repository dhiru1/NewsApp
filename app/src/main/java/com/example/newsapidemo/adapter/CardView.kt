package com.example.newsapidemo.adapter

import android.content.Intent
import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.example.newsapidemo.R
import com.squareup.picasso.Picasso

class CardView : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_card_view)
        val edit_title=findViewById<TextView>(R.id.text_title)
        val edit_author=findViewById<TextView>(R.id.text_author)
        val edit_description=findViewById<TextView>(R.id.text_description)
        val imageView=findViewById<ImageView>(R.id.imageToUrl)

        val intent:Intent=getIntent();

        val title=intent.getStringExtra("title")
        val author=intent.getStringExtra("author")
        val description=intent.getStringExtra("description")
        val urlToImage=intent.getStringExtra("urlImage")
        Picasso.with(this).load(urlToImage).into(imageView)
        edit_title.setText(title)
        edit_author.setText(author)
        edit_description.setText(description)
    }
}