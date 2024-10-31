package com.example.loteriaconcorrutina

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class LoteriaViewModel : ViewModel() {
    private val _lotoNumbers = mutableStateOf(emptyList<Int>())
    val lotoNumber: State<List<Int>> = _lotoNumbers

    private val _isLoading = mutableStateOf(false)
    val isLoading: State<Boolean> = _isLoading

    fun generateLotoNumbers() {
        viewModelScope.launch {
            _isLoading.value = true
            _lotoNumbers.value = emptyList()
            val generatedNumbers = mutableSetOf<Int>()


            repeat(6) {
                var newNumber: Int
                do {
                    newNumber = (1..60).random()
                } while (newNumber in generatedNumbers)

                generatedNumbers.add(newNumber)
                _lotoNumbers.value = _lotoNumbers.value + newNumber
                delay(2000)
            }

            _isLoading.value = false
        }
    }
}

