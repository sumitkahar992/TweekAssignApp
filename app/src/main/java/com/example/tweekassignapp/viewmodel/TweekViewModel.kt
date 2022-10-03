package com.example.tweekassignapp.viewmodel

import android.util.Log
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

    val _snapshotStateList = mutableStateListOf<MockyModelItem>()
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


    private val _elements = mutableStateListOf<Int>()
    val element : List<Int> = _elements


    fun onAdd() {
        val randomNumber = Random.nextInt(0,100)
        _elements.add(randomNumber)


        Log.d("TAG", "Added Number : $_snapshotStateList")
    }





    private val _cards = mutableStateListOf<SimpleData>()
    val cards : List<SimpleData> = _cards

    private fun onAddCard(): List<SimpleData> {

        _cards.add(
            SimpleData(image = developerImage.random(),
                developer = developerName.random(),
                id = Random.nextInt(1,15)))

        return cards
    }

    fun generateRandomCard(): SimpleData {
        return onAddCard().random()
    }






































}