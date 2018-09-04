package com.gfoundry.adg.mtgsender.models

import java.io.Serializable

//Quantity,Name,Set,External ID,Price Each
data class Card (val quantity:Int?,
                 val name:String,
                 val set:String,
                 val externalID:Int?,
                 val price:String):Serializable{
}