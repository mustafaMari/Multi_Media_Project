package com.multi_media.touristy

import android.content.ContentValues
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.ImageView
import android.widget.TextView
import com.google.firebase.database.*

class PlaceDetailActivity : AppCompatActivity() {
       lateinit var placeDetails : Map<String , Any>
       lateinit var placeTitle : TextView
       lateinit var placeDescription: TextView
       lateinit var placeRatings : TextView
       lateinit var placeImg : ImageView

        override fun onCreate(savedInstanceState: Bundle?) {
            super.onCreate(savedInstanceState)
            setContentView(R.layout.activity_place_detail)

            placeTitle = findViewById(R.id.place_name)
            placeDescription = findViewById(R.id.place_desc)
            placeRatings = findViewById(R.id.place_rating)
            placeImg = findViewById(R.id.place_image)

            val cityName = intent.getStringExtra("CITY")
            val placeType = intent.getStringExtra("PLACE_TYPE")
            val placeName = intent.getStringExtra("PLACE_NAME")

            var database: DatabaseReference = FirebaseDatabase.getInstance().reference
            val place_ref: DatabaseReference = database.child("cities").child(cityName!!).child(placeType!!).child(placeName!!)

            place_ref.addValueEventListener(object : ValueEventListener {
                override fun onCancelled(error: DatabaseError) {
                    Log.d(ContentValues.TAG, error.message)
                }

                override fun onDataChange(dataSnapshot: DataSnapshot) {
                    placeDetails = mutableMapOf<String, Any>()
                    for (postSnapshot in dataSnapshot.children) {
                        postSnapshot.key?.let { postSnapshot.value?.let { it1 ->
                            (placeDetails as MutableMap<String, Any>).put(it,
                                it1
                            )
                        } }
                    }
                   getPlaceDetail(placeDetails)
                }
            })

        }


    fun getPlaceDetail(placeDetail: Map<String, Any>){
        val title = placeDetail["place_name"] as String
        val description = placeDetail["place_description"] as String
        val rating =  placeDetail["place_rating"] as Long
        val ratingText = "Rated : $rating/10 "
        val resID = resources.getIdentifier(
            placeDetail["img_url"]as String?, "drawable",
            packageName
        )
         placeTitle.setText(title)
        placeDescription.setText(description)
        placeRatings.setText(ratingText)
        placeImg.setImageResource(resID)



    }

}