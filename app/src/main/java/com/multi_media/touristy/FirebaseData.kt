package com.multi_media.touristy

import android.content.ContentValues.TAG
import android.util.Log
import com.google.firebase.database.*

class FirebaseData {
    constructor(){

    }




    fun getCities() : ArrayList<String> {
        var database: DatabaseReference = FirebaseDatabase.getInstance().reference
       val cityRef : DatabaseReference = database.child("cities")
        val resultList = arrayListOf<String>()
        cityRef.addValueEventListener(object : ValueEventListener {
            override fun onCancelled(error: DatabaseError) {
                Log.d(TAG, error.message)
            }

            override fun onDataChange(dataSnapshot: DataSnapshot) {
                var resultList = arrayListOf<String>()
                for (postSnapshot in dataSnapshot.children) {
                    resultList.add(postSnapshot.key as String)
                }
                println(resultList)
            }

        })
        println(resultList)
        return resultList
    }

    fun getPlaceTypes(city_name: String?) : ArrayList<String> {
        var database: DatabaseReference = FirebaseDatabase.getInstance().reference
        val cityRef : DatabaseReference = database.child("cities").child(city_name!!)
        val resultList = arrayListOf<String>()
        cityRef.addValueEventListener(object : ValueEventListener {
            override fun onCancelled(error: DatabaseError) {
                Log.d(TAG, error.message)
            }

            override fun onDataChange(dataSnapshot: DataSnapshot) {
                val resultList = arrayListOf<String>()
                for (postSnapshot in dataSnapshot.children) {
                    var place : String? = postSnapshot.key.toString()
                    if (place != null && "type" in place) {
                        resultList.add(place.split("_")[1])
                    }
                }
                println(resultList)
            }

        })
        println(resultList)
        return resultList
    }

    fun getPlacesOfaPlaceType( cityName: String ? , placeType: String?): ArrayList<Map<String, Any>>{
        var database: DatabaseReference = FirebaseDatabase.getInstance().reference
        val place_ref: DatabaseReference = database.child("cities").child(cityName!!).child(placeType!!)
        var resultList = arrayListOf<Map<String, Any>>()
        println("Startin fetching")
        place_ref.addValueEventListener(object : ValueEventListener {
            override fun onCancelled(error: DatabaseError) {
                Log.d(TAG, error.message)
            }

            override fun onDataChange(dataSnapshot: DataSnapshot) {
                var resultList = arrayListOf<Map<String, Any>>()
                for (postSnapshot in dataSnapshot.children) {
                    resultList.add(postSnapshot.value as Map<String, Any>)
                }

            }

        })
        return resultList
    }
    fun getPlaceDetail( cityName: String ? , placeType: String? , placeName: String? ): Map<String, Any> {
        var database: DatabaseReference = FirebaseDatabase.getInstance().reference
        val place_ref: DatabaseReference = database.child("cities").child(cityName!!).child(placeType!!).child(placeName!!)
        var resultList = mutableMapOf<String, Any>()
        println("Startin fetching")
        place_ref.addValueEventListener(object : ValueEventListener {
            override fun onCancelled(error: DatabaseError) {
                Log.d(TAG, error.message)
            }

            override fun onDataChange(dataSnapshot: DataSnapshot) {
                var resultList = mutableMapOf<String, Any>()
                for (postSnapshot in dataSnapshot.children) {
                    postSnapshot.key?.let { postSnapshot.value?.let { it1 ->
                        resultList.put(it,
                            it1
                        )
                    } }
                }

            }


        })
        return resultList
    }


}
