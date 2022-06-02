package com.multi_media.touristy

import android.content.ContentValues
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.AutoCompleteTextView
import android.widget.ListView
import android.widget.Toast
import com.google.firebase.database.*

import com.multi_media.touristy.Constants.Companion.CITY
import com.multi_media.touristy.Constants.Companion.PLACE_TYPE
import com.multi_media.touristy.Constants.Companion.PLACE_NAME

import android.R.drawable
import android.content.Intent
import android.content.res.Resources
import java.lang.reflect.Field


class PlacesActivity : AppCompatActivity() {
    lateinit var places: ArrayList<Map<String, Any>>
    lateinit var listView: ListView
    lateinit var placeNames: ArrayList<String>
    lateinit var placeDesc: ArrayList<String>
    lateinit var placeImgIds: ArrayList<Int>
    lateinit var cityName : String
    lateinit var placeType : String
    var resID  = 0
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_places)

        listView = findViewById<ListView>(R.id.listView)
        cityName = intent.getStringExtra("CITY").toString()
        placeType = "type_" + intent.getStringExtra("PLACE_TYPE")
        var database: DatabaseReference = FirebaseDatabase.getInstance().reference
        val placeRef: DatabaseReference =
            database.child("cities").child(cityName!!).child(placeType)
        places = arrayListOf<Map<String, Any>>()
        placeRef.addValueEventListener(object : ValueEventListener {
            override fun onCancelled(error: DatabaseError) {
                Log.d(ContentValues.TAG, error.message)
            }

            override fun onDataChange(dataSnapshot: DataSnapshot) {
                for (postSnapshot in dataSnapshot.children) {
                    places.add(postSnapshot.value as Map<String, Any>)
                }
                println("these are places" + places)
                get_arrays_and_display(places)
            }

        })


//        }
    }

    fun get_arrays_and_display(places: ArrayList<Map<String, Any>>) {

//        val drawableClass = drawable::class.java
//        val instance = drawableClass.declaredConstructors[0].newInstance() as drawable
//        val drawables: List<Int> = ArrayList()
        println("places inside method"+places)
        placeNames = arrayListOf()
        placeDesc = arrayListOf()
        placeImgIds = arrayListOf()
        for (place in places) {

            println("this is place name " + place["place_name"])
            placeNames.add(place["place_name"] as String)
            resID = resources.getIdentifier(
                place["img_url"] as String?, "drawable",
                packageName
            )
            placeImgIds.add(resID)

        }
        val myListAdapter = MyListAdapter(this, placeNames, placeImgIds)
        listView.adapter = myListAdapter

        listView.setOnItemClickListener() { adapterView, view, position, id ->
            val itemAtPos = adapterView.getItemAtPosition(position)
            val itemIdAtPos = adapterView.getItemIdAtPosition(position)
            Toast.makeText(
                this,
                "Click on item at $itemAtPos its item id $itemIdAtPos",
                Toast.LENGTH_LONG
            ).show()
            val mCity = cityName
            val mPlaceType = placeType
            val intent = Intent(this, PlaceDetailActivity::class.java).apply {
                putExtra(CITY, mCity)
                putExtra(PLACE_TYPE, mPlaceType)
                putExtra(PLACE_NAME, itemAtPos.toString())

            }
            finish()
            startActivity(intent)
        }
        }
    }

