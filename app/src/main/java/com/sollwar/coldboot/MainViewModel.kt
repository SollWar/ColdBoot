package com.sollwar.coldboot

import androidx.lifecycle.ViewModel

class MainViewModel : ViewModel() {

    private var coldBootCounter = 1
    private var isFirstIncrease = true

    fun getColdBootCounter(): Int = coldBootCounter

    fun increaseColdBootCounter(saveCounter: Int) {
        if (isFirstIncrease) {
            coldBootCounter += saveCounter
            isFirstIncrease = false
        }
    }

}