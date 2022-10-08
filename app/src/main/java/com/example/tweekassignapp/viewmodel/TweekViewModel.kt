package com.example.tweekassignapp.viewmodel

import androidx.compose.runtime.mutableStateListOf
import androidx.lifecycle.LifecycleObserver
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.tweekassignapp.cat.SimpleData
import com.example.tweekassignapp.cat.developerImage
import com.example.tweekassignapp.cat.developerName
import com.example.tweekassignapp.common.NetworkResult
import com.example.tweekassignapp.data.model.MockyModelItem
import com.example.tweekassignapp.data.repository.MainRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject
import kotlin.random.Random


@HiltViewModel
class TweekViewModel @Inject constructor(
    private val mainRepository: MainRepository
) : ViewModel(), LifecycleObserver {

    init {
        getPlayer()
    }

    private val _snapshotStateList = mutableStateListOf<MockyModelItem>()
    val snapshotStateList: List<MockyModelItem> = _snapshotStateList


    private fun getPlayer() = viewModelScope.launch {
        when (val result = mainRepository.getPlayer()) {
            is NetworkResult.Success -> {
                result.data?.let { _snapshotStateList.addAll(it) }
            }

            else -> {

            }
        }
    }

    fun sortMocky(): List<MockyModelItem> {
        _snapshotStateList.sortByDescending { it.score }
        return snapshotStateList
    }







    private val _cards = mutableStateListOf<SimpleData>()
    val cards: List<SimpleData> = _cards

    private fun onAddCard(): List<SimpleData> {

        _cards.add(
            SimpleData(
                image = developerImage.random(),
                developer = developerName.random(),
                id = Random.nextInt(1, 15)
            )
        )

        return cards
    }

    fun sortBy(): List<SimpleData> {
        _cards.sortBy { it.id }
        return cards
    }


    fun generateRandomCard(): SimpleData {
        return onAddCard().random()
    }


}