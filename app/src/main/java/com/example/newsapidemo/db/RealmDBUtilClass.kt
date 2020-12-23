package com.example.newsapidemo.db

import com.example.newsapidemo.model.ArticleObject
import com.example.newsapidemo.model.RealmDataObject
import io.realm.Realm
import io.realm.RealmConfiguration

class RealmDBUtilClass {

    companion object{
        @Volatile
        private var INSTANCE: RealmDBUtilClass? = null
        fun getInstance() =
            INSTANCE ?: synchronized(this) {
                INSTANCE ?: RealmDBUtilClass().also {
                    INSTANCE = it
                }
            }
    }

    fun configuration():Realm{
        val configuration= RealmConfiguration.Builder()
            .name("RealmDataObject.realm").build()
        val realm= Realm.getInstance(configuration)
        return realm
    }
    fun createRealmObject(articleObject: ArticleObject?) {
        val realm= configuration()
        realm.beginTransaction()
        val maxId=realm.where(RealmDataObject::class.java).max("id")
        val key=if (maxId==null) 1 else maxId.toInt()+1
        val obj=realm.createObject(RealmDataObject::class.java,key)
        obj.author=articleObject?.author
        obj.title=articleObject?.title
        obj.description=articleObject?.description
        obj.content=articleObject?.content
        obj.url=articleObject?.url
        obj.urlToImage=articleObject?.urlToImage
        obj.publishedAt=articleObject?.publishedAt
        obj.name=articleObject?.sourceObject?.name
        obj.Id=articleObject?.sourceObject?.id
        realm.commitTransaction()
        realm.close()
    }
    fun deleteData(){

        val realm= configuration()
        realm.beginTransaction()
        realm.delete(RealmDataObject::class.java)
        realm.commitTransaction()
        realm.close()
    }
    fun readData():List<RealmDataObject>{
        val realm= configuration()
        realm.beginTransaction()
        val read=realm.where(RealmDataObject::class.java).findAll()
        val list:List<RealmDataObject> = realm.copyFromRealm(read)
        realm.commitTransaction()
        realm.close()
        return list
    }

}