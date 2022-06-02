package com.multi_media.touristy

import android.content.ContentValues
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.*
import com.google.firebase.database.*
import kotlin.collections.ArrayList
import com.multi_media.touristy.Constants.Companion.CITY
import com.multi_media.touristy.Constants.Companion.PLACE_TYPE


class MainActivity : AppCompatActivity() {

    lateinit var cityList: ArrayList<String>
     var placeTypes : ArrayList<String> = arrayListOf()
     var searchButton : Button? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        searchButton = findViewById<Button>(R.id.id_search)


        var database: DatabaseReference = FirebaseDatabase.getInstance().reference
            val cityRef : DatabaseReference = database.child("cities")
           cityList= arrayListOf<String>()
            cityRef.addValueEventListener(object : ValueEventListener {
                override fun onCancelled(error: DatabaseError) {
                    Log.d(ContentValues.TAG, error.message)
                }

                override fun onDataChange(dataSnapshot: DataSnapshot) {
                    for (postSnapshot in dataSnapshot.children) {
                        cityList.add(postSnapshot.key as String)
                    }

                }

            })


        displayCitiesAndReturnCity(cityList)



    }

    private fun displayCitiesAndReturnCity(cityList: ArrayList<String>) {
        val languages = cityList

        val arrayAdapter = ArrayAdapter(this, R.layout.dropdown_item_city, languages)
        // get reference to the autocomplete text view
        val autocompleteTV = findViewById<AutoCompleteTextView>(R.id.autoCompleteTextView_city)
        // set adapter to the autocomplete tv to the arrayAdapter
        autocompleteTV.setAdapter(arrayAdapter)
        autocompleteTV.onItemClickListener =
            AdapterView.OnItemClickListener { parent, view, position, id ->

                val selectedCity = parent.getItemAtPosition(position) as String
                displayPlacetypes(selectedCity)
                Toast.makeText(this, "${selectedCity}  is Selected.", Toast.LENGTH_SHORT)

                    .show()

            }

    }


    fun search(city: String, placeType : String) {
        searchButton?.setOnClickListener{

            // TODO validation on date + send date in intent
            val mCity = city
            val mPlaceType = placeType
            val intent = Intent(this, PlacesActivity::class.java).apply {
                putExtra(CITY, mCity)
                putExtra(PLACE_TYPE, mPlaceType)

            }
            finish()
            startActivity(intent)
        }

    }


    private fun displayPlacetypes(selectedCity : String) {

        var placeTypes = getPlaceTypes(selectedCity)
        val placeAdapter = ArrayAdapter(this, R.layout.dropdown_item_place_type, placeTypes)
        val autocompleteTV2 =
            findViewById<AutoCompleteTextView>(R.id.autoCompleteTextView_placeType)
        autocompleteTV2.setAdapter(placeAdapter)
        autocompleteTV2.onItemClickListener =
            AdapterView.OnItemClickListener { parent, view, position, id ->
                // Get the selected item text from ListView
               var  selectedPlace = parent.getItemAtPosition(position) as String
                Toast.makeText(this, "$selectedPlace  is Selected.", Toast.LENGTH_SHORT)
                    .show()
                search(selectedCity, selectedPlace)
            }
    }

    private fun getPlaceTypes(city_name: String?) : ArrayList<String> {
        var database: DatabaseReference = FirebaseDatabase.getInstance().reference
        val cityRef : DatabaseReference = database.child("cities").child(city_name!!)
        placeTypes = arrayListOf()
        cityRef.addValueEventListener(object : ValueEventListener {
            override fun onCancelled(error: DatabaseError) {
                Log.d(ContentValues.TAG, error.message)
            }

            override fun onDataChange(dataSnapshot: DataSnapshot) {
                for (postSnapshot in dataSnapshot.children) {
                    var place : String? = postSnapshot.key.toString()
                    if (place != null && "type" in place) {
                        placeTypes.add(place.split("_")[1])
                    }
                }

            }

        })

        return placeTypes
    }


}