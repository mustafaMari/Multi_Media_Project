package com.multi_media.touristy



import android.os.Build
import android.widget.Toast
import androidx.annotation.RequiresApi
import java.lang.Double.parseDouble
import java.lang.Exception
import java.lang.IllegalArgumentException
import java.text.DateFormatSymbols
import java.text.SimpleDateFormat
import java.time.LocalDate
import java.util.*
import kotlin.collections.ArrayList

class Utils {

    fun isValidString(target: Any): Boolean {
        return target is String
    }

    fun isNotEmpty(target : Any): Boolean{
        return (target.toString()).isNotEmpty()

    }

    fun checkPlacesTypes(place : String) : String {
        val placeTypes = arrayOf("Museums" , "Restaurants" , "Others")
        if (place in placeTypes) {
           return "Correct place type"
        }
        else {
            throw  Exception("This is an invalid place type")

        }

    }

    fun checkPlaceRating(rating : Any): Boolean {
        return rating is Int && (rating in 1..10)
    }


}