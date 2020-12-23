package com.example.newsapidemo.model

import io.realm.RealmObject
import io.realm.annotations.PrimaryKey
import io.realm.annotations.RealmClass

@RealmClass
open class RealmDataObject() :RealmObject(){
    @PrimaryKey
    var id= 0
    var author : String?=null
    var title : String?=null
    var description : String?=null
    var url : String?=null
    var urlToImage : String?=null
    var publishedAt : String?=null
    var content : String?=null
    var name:String?=null
    var Id:String?=null

}
