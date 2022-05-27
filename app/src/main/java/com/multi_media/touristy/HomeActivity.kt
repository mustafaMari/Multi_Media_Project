package com.multi_media.touristy

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import com.multi_media.touristy.FirebaseData
import com.google.firebase.database.*
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase

class HomeActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)
        val f = FirebaseData()

//          f.getCities()
//            println("places now")
//            f.getPlaceTypes("Wroclaw")
//            f.getPlacesOfaPlaceType("Wroclaw", "type_Museums")
              (f.getPlaceDetail("Wroclaw", "type_Museums", "Wroclaw Architecture Museum"))
               val buttonClick = findViewById<Button>(R.id.id_explore)
        buttonClick.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }
    }
}

