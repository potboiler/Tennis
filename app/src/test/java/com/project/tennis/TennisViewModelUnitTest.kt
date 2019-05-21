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
        tennisViewModel.playerOneScores()
        assertEquals(TennisViewModel.POINT_ONE + " - " + TennisViewModel.POINT_LOVE,
            tennisViewModel.getMatchScore())
    }

    @Test
    fun testPlayerTwoWinsFirstBall() {
        tennisViewModel.playerTwoScores()
        assertEquals(TennisViewModel.POINT_LOVE + " - " + TennisViewModel.POINT_ONE,
            tennisViewModel.getMatchScore())
    }

    @Test
    fun testIfBothPlayersScoredOnePointEach() {
        tennisViewModel.playerOneScores()
        tennisViewModel.playerTwoScores()
        assertEquals(TennisViewModel.POINT_ONE + " " + TennisViewModel.POINT_EQUAL,
            tennisViewModel.getMatchScore())
    }

    @Test
    fun testIfPlayerOneHasScoredFirstTwoPoints() {
        tennisViewModel.playerOneScores()
        tennisViewModel.playerOneScores()
        assertEquals(TennisViewModel.POINT_TWO + " - " + TennisViewModel.POINT_LOVE,
            tennisViewModel.getMatchScore())
    }

    @Test
    fun testIfPlayerOneHasScoredFirstThreePoints() {
        tennisViewModel.playerOneScores()
        tennisViewModel.playerOneScores()
        tennisViewModel.playerOneScores()
        assertEquals(TennisViewModel.POINT_THREE + " - " + TennisViewModel.POINT_LOVE, tennisViewModel.getMatchScore())
    }

    @Test
    fun testShouldReturnAllInScoreIfBothPlayersScoresEqualPoints() {
        tennisViewModel.playerOneScores()
        tennisViewModel.playerOneScores()
        tennisViewModel.playerTwoScores()
        tennisViewModel.playerTwoScores()
        assertEquals(TennisViewModel.POINT_TWO + " " + TennisViewModel.POINT_EQUAL, tennisViewModel.getMatchScore())
    }

    @Test
    fun testShouldReturnDeuceIfBothPlayersScoresThreePointsEach() {
        tennisViewModel.playerOneScores()
        tennisViewModel.playerOneScores()
        tennisViewModel.playerOneScores()
        tennisViewModel.playerTwoScores()
        tennisViewModel.playerTwoScores()
        tennisViewModel.playerTwoScores()
        assertEquals(TennisViewModel.MATCH_STATUS_DEUCE, tennisViewModel.getMatchScore())
    }

    @Test
    fun testShouldReturnAdvantagePlayerTwoIfTheyScoreAPointAfterDeuce() {
        tennisViewModel.playerOneScores()
        tennisViewModel.playerOneScores()
        tennisViewModel.playerOneScores()
        tennisViewModel.playerTwoScores()
        tennisViewModel.playerTwoScores()
        tennisViewModel.playerTwoScores()
        tennisViewModel.playerTwoScores()
        assertEquals(TennisViewModel.MATCH_STATUS_ADVANTAGE + " " + Players.PLAYER_TWO_NAME,
            tennisViewModel.getMatchScore())
    }

    @Test
    fun testShouldReturnAdvantagePlayerOneIfTheyScoreAPointAfterDeuce() {
        tennisViewModel.playerOneScores()
        tennisViewModel.playerOneScores()
        tennisViewModel.playerOneScores()
        tennisViewModel.playerTwoScores()
        tennisViewModel.playerTwoScores()
        tennisViewModel.playerTwoScores()
        tennisViewModel.playerOneScores()
        assertEquals(TennisViewModel.MATCH_STATUS_ADVANTAGE + " " + Players.PLAYER_ONE_NAME,
            tennisViewModel.getMatchScore())
    }

    @Test
    fun testShouldReturnPlayerTwoWinsIfTheyScoreAPointAfterAdvantage() {
        tennisViewModel.playerOneScores()
        tennisViewModel.playerOneScores()
        tennisViewModel.playerOneScores()
        tennisViewModel.playerTwoScores()
        tennisViewModel.playerTwoScores()
        tennisViewModel.playerTwoScores()
        tennisViewModel.playerTwoScores()
        tennisViewModel.playerTwoScores()
        assertEquals(Players.PLAYER_TWO_NAME + " " + TennisViewModel.MATCH_STATUS_WON,
            tennisViewModel.getMatchScore())
    }

    @Test
    fun testShouldReturnPlayerOneWinsIfTheyScoreAPointAfterAdvantage() {
        tennisViewModel.playerOneScores()
        tennisViewModel.playerOneScores()
        tennisViewModel.playerOneScores()
        tennisViewModel.playerTwoScores()
        tennisViewModel.playerTwoScores()
        tennisViewModel.playerTwoScores()
        tennisViewModel.playerOneScores()
        tennisViewModel.playerOneScores()
        assertEquals(Players.PLAYER_ONE_NAME + " " + TennisViewModel.MATCH_STATUS_WON, tennisViewModel.getMatchScore())
    }
}
