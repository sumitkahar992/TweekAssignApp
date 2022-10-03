package com.example.tweekassignapp.data.repository

import androidx.compose.runtime.snapshots.SnapshotStateList
import com.example.tweekassignapp.common.BaseApiResponse
import com.example.tweekassignapp.common.NetworkResult
import com.example.tweekassignapp.data.api.ApiService
import com.example.tweekassignapp.data.model.*
import dagger.hilt.android.scopes.ActivityRetainedScoped
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext
import javax.inject.Inject


@ActivityRetainedScoped
class MainRepository @Inject constructor(
    private val apiService: ApiService,
    private val defaultDispatcher: CoroutineDispatcher
) : BaseApiResponse()
{                                          // arrayListOf       //mutableListOf
    suspend fun getPlayer() : NetworkResult<SnapshotStateList<MockyModelItem>> {
        return withContext(defaultDispatcher) { safeApiCall { apiService.getPlayer() } }
    }

}