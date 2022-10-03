package com.example.tweekassignapp.data.api

import androidx.compose.runtime.snapshots.SnapshotStateList
import com.example.tweekassignapp.data.model.*
import retrofit2.Response
import retrofit2.http.GET

interface ApiService {


//    @GET("/shwetankshrey/adff97bc000273ac85b05e408582c23f/raw/c47eb153d1f55a9a085617c4449ab81eaa0dc094/leaderboard_d.json")
//    @GET("/demos/marvel/")
//    @GET("/v3/d4bff780-d588-463a-bb9b-33ac85d8210c")





    @GET("/v3/3debbad7-f53c-46ab-bfc7-3d164c9c6dc8")
    suspend fun getPlayer(): Response<SnapshotStateList<MockyModelItem>>
}