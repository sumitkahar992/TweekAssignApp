package com.example.tweekassignapp.data.model

import com.google.gson.annotations.SerializedName

data class MockyModelItem(

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

val dummyData = MockyModelItem(
    bfc = 88,
    ffc = 77,
    id = "01XC",
    jump = 66,
    name = "AngshumaN BarmaN",
    release = 44,
    runup = 22,
    score = 888
)