package com.example.tweekassignapp.data.model

import com.google.gson.annotations.SerializedName

data class TweekModel(

    @field:SerializedName("bfc")
    val bfc: Int,

    @field:SerializedName("ffc")
    val ffc: Int,

    @field:SerializedName("id")
    val id: String,

    @field:SerializedName("jump")
    val jump: Int,

    @field:SerializedName("name")
    val name: String,

    @field:SerializedName("release")
    val release: Int,

    @field:SerializedName("runup")
    val runup: Int,

    @field:SerializedName("score")
    val score: Int

)

data class Athlete(

    @field:SerializedName("athletes")
    val athletes: List<TweekModel>,

    @field:SerializedName("organisation")
    val organisation: String
)


















