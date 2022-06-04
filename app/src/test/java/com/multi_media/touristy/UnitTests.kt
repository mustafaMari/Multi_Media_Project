package com.multi_media.touristy

import com.multi_media.touristy.Utils
import org.junit.Test

import org.junit.Assert.*
import java.lang.Exception
import java.lang.IllegalArgumentException


class UnitTests{

    @Test
    fun validString() {
        var myUtils: Utils = Utils()
       assert((myUtils.isValidString("String")))
        assert(!(myUtils.isValidString(0)))
        assert(!(myUtils.isValidString(6.88)))
        assert(myUtils.isValidString("Mustafa"))
    }

    @Test
    fun nonEmptyValues(){
        var myUtils: Utils = Utils()
       assert(!(myUtils.isNotEmpty("")))
        assert(myUtils.isNotEmpty("Stop violence"))
    }

    @Test(expected = Exception::class)
    fun incorrectPlaceTypes(){
        var myUtils: Utils = Utils()
        myUtils.checkPlacesTypes("Aquamannnn")
        myUtils.checkPlacesTypes("My dog stepped on a BEE")

    }

  @Test
    fun rightPlaceTypes2()
    {
        var myUtils: Utils = Utils()
        myUtils.checkPlacesTypes("Museums")
        myUtils.checkPlacesTypes("Others")

    }

    @Test
    fun checkPlaceRating() {
        var myUtils: Utils = Utils()
        assert(!(myUtils.checkPlaceRating("not a int")))
        assert(myUtils.checkPlaceRating(3))
        assert(!(myUtils.checkPlaceRating(4.6)))

    }

}