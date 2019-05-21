package com.project.tennis.viewmodel

import android.arch.lifecycle.ViewModel

class TennisViewModel : ViewModel() {
    var playerOnePoints = 0
    var playerTwoPoints = 0

    fun playerOneScores() {
        playerOnePoints += 1
    }

    fun playerTwoScores() {
        playerTwoPoints += 1
    }

    fun getMatchScore(): String {

        if(playerHasWon()){
            return playerWithHighestScore() + " Wins"
        }
        if (playerHasAdvantage()) {
            return "Advantage for " + playerWithHighestScore()
        }
        if (isDeuce()) {
            return "Deuce"
        }
        if (checkForEqualPoints()) {
            return convertPointsToScore(playerOnePoints) + " all"
        }
        return convertPointsToScore(playerOnePoints) + ", " + convertPointsToScore(playerTwoPoints)
    }

    private fun isDeuce(): Boolean {
        return checkForThreeOrMorePoints(playerOnePoints) && checkForEqualPoints()
    }

    private fun playerHasAdvantage(): Boolean {
        if (checkForThreeOrMorePoints(playerOnePoints) && checkForOnePointMoreThanOther(playerOnePoints, playerTwoPoints)) {
            return true
        }
        if (checkForThreeOrMorePoints(playerTwoPoints) && checkForOnePointMoreThanOther(playerTwoPoints, playerOnePoints)) {
            return true
        }
        return false
    }

    private fun playerHasWon(): Boolean {
        if (checkForFourOrMorePoints(playerOnePoints) && checkForMoreThanOnePointThanOther(playerOnePoints, playerTwoPoints)) {
            return true
        }
        if (checkForFourOrMorePoints(playerTwoPoints) && checkForMoreThanOnePointThanOther(playerTwoPoints, playerOnePoints)) {
            return true
        }
        return false
    }

    private fun playerWithHighestScore(): String {
        if(playerOnePoints > playerTwoPoints){
            return "PlayerOne"
        } else{
            return "PlayerTwo"
        }
    }

    private fun checkForFourOrMorePoints(points: Int): Boolean {
        return points >= 4
    }

    private fun checkForThreeOrMorePoints(points: Int): Boolean {
        return points >= 3
    }

    private fun checkForEqualPoints(): Boolean {
        return playerOnePoints == playerTwoPoints
    }

    private fun checkForOnePointMoreThanOther(valueOne: Int, valueTwo: Int): Boolean {
        return valueOne == valueTwo + 1
    }

    private fun checkForMoreThanOnePointThanOther(valueOne: Int, valueTwo: Int): Boolean {
        return valueOne >= valueTwo + 2
    }

    private fun convertPointsToScore(points: Int): String {
        when (points) {
            0 -> return "0"
            1 -> return "15"
            2 -> return "30"
            3 -> return "40"
        }
        return ""
    }

    fun resetScore(){
        playerOnePoints = 0
        playerTwoPoints = 0
    }

}