package com.project.tennis

import com.project.tennis.model.Players
import com.project.tennis.viewmodel.TennisViewModel
import org.junit.Test

import org.junit.Assert.*
import org.junit.Before


class TennisViewModelUnitTest {

    private var tennisViewModel = TennisViewModel()

    @Before
    fun resetScore() {
        tennisViewModel.resetScore()
    }
    @Test
    fun testShouldCheckIfPlayersPointsAreAtLoveForNewGame() {
        assertEquals(TennisViewModel.POINT_LOVE + " " + TennisViewModel.POINT_EQUAL,
            tennisViewModel.getMatchScore())
    }

    @Test
    fun testPlayerOneWinsFirstBall() {
        recordPoints(1, 0)
        assertEquals(TennisViewModel.POINT_ONE + " - " + TennisViewModel.POINT_LOVE,
            tennisViewModel.getMatchScore())
    }

    @Test
    fun testPlayerTwoWinsFirstBall() {
        recordPoints(0, 1)
        assertEquals(TennisViewModel.POINT_LOVE + " - " + TennisViewModel.POINT_ONE,
            tennisViewModel.getMatchScore())
    }

    @Test
    fun testIfBothPlayersScoredOnePointEach() {
        recordPoints(1, 1)
        assertEquals(TennisViewModel.POINT_ONE + " " + TennisViewModel.POINT_EQUAL,
            tennisViewModel.getMatchScore())
    }

    @Test
    fun testIfPlayerOneHasScoredFirstTwoPoints() {
        recordPoints(2, 0)
        assertEquals(TennisViewModel.POINT_TWO + " - " + TennisViewModel.POINT_LOVE,
            tennisViewModel.getMatchScore())
    }

    @Test
    fun testIfPlayerOneHasScoredFirstThreePoints() {
        recordPoints(3, 0)
        assertEquals(TennisViewModel.POINT_THREE + " - " + TennisViewModel.POINT_LOVE, tennisViewModel.getMatchScore())
    }

    @Test
    fun testShouldReturnAllInScoreIfBothPlayersScoresEqualPoints() {
        recordPoints(2, 2)
        assertEquals(TennisViewModel.POINT_TWO + " " + TennisViewModel.POINT_EQUAL, tennisViewModel.getMatchScore())
    }

    @Test
    fun testShouldReturnDeuceIfBothPlayersScoresThreePointsEach() {
        recordPoints(3, 3)
        assertEquals(TennisViewModel.MATCH_STATUS_DEUCE, tennisViewModel.getMatchScore())
    }

    @Test
    fun testShouldReturnAdvantagePlayerTwoIfTheyScoreAPointAfterDeuce() {
        recordPoints(3, 4)
        assertEquals(TennisViewModel.MATCH_STATUS_ADVANTAGE + " " + Players.PLAYER_TWO_NAME,
            tennisViewModel.getMatchScore())
    }

    @Test
    fun testShouldReturnAdvantagePlayerOneIfTheyScoreAPointAfterDeuce() {
        recordPoints(4, 3)
        assertEquals(TennisViewModel.MATCH_STATUS_ADVANTAGE + " " + Players.PLAYER_ONE_NAME,
            tennisViewModel.getMatchScore())
    }

    @Test
    fun testShouldReturnPlayerTwoWinsIfTheyScoreAPointAfterAdvantage() {
        recordPoints(3, 5)
        assertEquals(Players.PLAYER_TWO_NAME + " " + TennisViewModel.MATCH_STATUS_WON,
            tennisViewModel.getMatchScore())
    }

    @Test
    fun testShouldReturnPlayerOneWinsIfTheyScoreAPointAfterAdvantage() {
        recordPoints(5, 3)
        assertEquals(Players.PLAYER_ONE_NAME + " " + TennisViewModel.MATCH_STATUS_WON, tennisViewModel.getMatchScore())
    }

    private fun recordPoints(playerOne: Int, playerTwo: Int){
        for(i in 1..playerOne){
            tennisViewModel.playerOneScores()
        }
        for (i in 1..playerTwo){
            tennisViewModel.playerTwoScores()
        }
    }
}
