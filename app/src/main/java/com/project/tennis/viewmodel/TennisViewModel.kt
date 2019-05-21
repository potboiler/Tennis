package com.project.tennis.viewmodel

import android.arch.lifecycle.ViewModel

class TennisViewModel: ViewModel(){
    var playerOnePoints = 0
    var playerTwoPoints = 0

    fun playerOneScores() {
        playerOnePoints += 1
    }

    fun checkPlayerOneScore() : String {
        return convertPointsToScore(playerOnePoints)
    }

    private fun convertPointsToScore(points: Int): String{
        when(points) {
            0 -> return "0"
            1 -> return "15"
            2 -> return "30"
            3 -> return "40"
        }
        return ""
    }

}