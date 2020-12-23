package com.example.newsapidemo.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.newsapidemo.R
import com.example.newsapidemo.model.RealmDataObject
import com.squareup.picasso.Picasso


class RecyclerAdapter(val realmDataList: List<RealmDataObject>?, val listener: OnItemClickListener) :
    RecyclerView.Adapter<RecyclerAdapter.ViewHolder>() {
    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        val textView = itemView.findViewById<TextView>(R.id.text_view)
        val textAuthor = itemView.findViewById<TextView>(R.id.textAuthor)
        val textDecription = itemView.findViewById<TextView>(R.id.textDescription)
        val imageView=itemView.findViewById<ImageView>(R.id.imageUrl)
        var imageurl:String?=null
        fun bind(article: RealmDataObject) {
            textView.setText(article.title)
            textAuthor.setText(article.author)
            textDecription.setText(article.description)
            imageurl=article.urlToImage
            Picasso.with(itemView.context).load(imageurl).into(imageView)
            itemView.setOnClickListener {
                listener.onItemClick(article)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val v = LayoutInflater.from(parent.context).inflate(R.layout.list_layout, parent, false)
        return ViewHolder(
            v
        )
    }

    override fun getItemCount(): Int {
        return realmDataList?.size!!
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val article: RealmDataObject = realmDataList!!.get(position)
        holder.bind(article)

    }

    interface OnItemClickListener {
        fun onItemClick(real: RealmDataObject)
    }

}