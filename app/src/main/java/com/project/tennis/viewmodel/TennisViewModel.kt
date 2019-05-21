package com.project.tennis.viewmodel

import android.arch.lifecycle.ViewModel
import com.project.tennis.model.Players

class TennisViewModel : ViewModel() {
    var playerOnePoints = 0
    var playerTwoPoints = 0

    companion object {
        const val POINT_LOVE = "0"
        const val POINT_ONE = "15"
        const val POINT_TWO = "30"
        const val POINT_THREE = "40"
        const val POINT_EQUAL = "All"
        const val MATCH_STATUS_DEUCE = "Deuce"
        const val MATCH_STATUS_ADVANTAGE = "Advantage"
        const val MATCH_STATUS_WON = "Wins"
    }

    fun playerOneScores() {
        playerOnePoints += 1
    }

    fun playerTwoScores() {
        playerTwoPoints += 1
    }

    fun getMatchScore(): String {

        if(playerHasWon()){
            return playerWithHighestScore() + " " + MATCH_STATUS_WON
        }
        if (playerHasAdvantage()) {
            return MATCH_STATUS_ADVANTAGE + " " + playerWithHighestScore()
        }
        if (isDeuce()) {
            return MATCH_STATUS_DEUCE
        }
        if (checkForEqualPoints()) {
            return convertPointsToScore(playerOnePoints) + " " + POINT_EQUAL
        }
        return convertPointsToScore(playerOnePoints) + " - " + convertPointsToScore(playerTwoPoints)
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
            return Players.PLAYER_ONE_NAME
        } else{
            return Players.PLAYER_TWO_NAME
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
            0 -> return POINT_LOVE
            1 -> return POINT_ONE
            2 -> return POINT_TWO
            3 -> return POINT_THREE
        }
        return ""
    }

    fun resetScore(){
        playerOnePoints = 0
        playerTwoPoints = 0
    }

}