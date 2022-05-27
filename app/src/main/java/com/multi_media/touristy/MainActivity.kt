package com.multi_media.touristy

import android.content.ContentValues
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.ArrayAdapter
import android.widget.AutoCompleteTextView
import android.widget.Toast
import com.google.firebase.database.*


class MainActivity : AppCompatActivity() {

    lateinit var cityList: ArrayList<String>

    var selectedPlace : String = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


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


        var x = displayCitiesAndReturnCity(cityList)
        println("textdbcsodicf" +x )





    }

    fun displayCitiesAndReturnCity(cityList: ArrayList<String>) : String{
        val languages = cityList
        var selected_city: String = ""

        // create an array adapter and pass the required parameter
        // in our case pass the context, drop down layout , and array.
        val arrayAdapter = ArrayAdapter(this, R.layout.dropdown_item_city, languages)
        // get reference to the autocomplete text view
        val autocompleteTV = findViewById<AutoCompleteTextView>(R.id.autoCompleteTextView_city)
        // set adapter to the autocomplete tv to the arrayAdapter
        autocompleteTV.setAdapter(arrayAdapter)
        autocompleteTV.setOnItemClickListener { parent, view, position, id ->
            // Get the selected item text from ListView
            val selectedCity = parent.getItemAtPosition(position)
            selected_city = selectedCity.toString()
//            println(selected_city)
            Toast.makeText(this, "$selectedCity  is Selected.", Toast.LENGTH_SHORT)
                .show()
        }
        println("AAAAAAAAAAAAAAAAAAAAA")

       return selected_city
    }

    fun displayPlacetypes(selectedCity : String) {

        var placeTypes = getPlaceTypes(selectedCity)
        val placeAdapter = ArrayAdapter(this, R.layout.dropdown_item_place_type, placeTypes)
        val autocompleteTV2 =
            findViewById<AutoCompleteTextView>(R.id.autoCompleteTextView_placeType)
        autocompleteTV2.setAdapter(placeAdapter)
        autocompleteTV2.setOnItemClickListener { parent, view, position, id ->
            // Get the selected item text from ListView
            selectedPlace = parent.getItemAtPosition(position) as String

            Toast.makeText(this, "$selectedPlace  is Selected.", Toast.LENGTH_SHORT)
                .show()

        }
    }

    fun getPlaceTypes(city_name: String?) : ArrayList<String> {
        var database: DatabaseReference = FirebaseDatabase.getInstance().reference
        val cityRef : DatabaseReference = database.child("cities").child(city_name!!)
        val resultList = arrayListOf<String>()
        cityRef.addValueEventListener(object : ValueEventListener {
            override fun onCancelled(error: DatabaseError) {
                Log.d(ContentValues.TAG, error.message)
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

        return resultList
    }


}