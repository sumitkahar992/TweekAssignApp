package com.example.tweekassignapp.viewmodel

import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.lifecycle.LifecycleObserver
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.tweekassignapp.common.NetworkResult
import com.example.tweekassignapp.data.model.*
import com.example.tweekassignapp.data.repository.MainRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class TweekViewModel @Inject constructor(
    private val mainRepository: MainRepository
) : ViewModel(), LifecycleObserver {

    init {
        getPlayer()
    }

    val snapshotStateList = SnapshotStateList<MockyModelItem>()

    private fun getPlayer() = viewModelScope.launch {
        when (val result = mainRepository.getPlayer()) {
            is NetworkResult.Success -> {
                result.data?.let { snapshotStateList.addAll(it) }
            }

            else -> {

            }
        }

    }


}