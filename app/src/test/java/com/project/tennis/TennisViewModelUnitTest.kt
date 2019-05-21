package com.project.tennis

import com.project.tennis.viewmodel.TennisViewModel
import org.junit.Test

import org.junit.Assert.*


class TennisViewModelUnitTest {

    private var tennisViewModel = TennisViewModel()

    @Test
    fun testShouldCheckIfBothPlayersScoreAreLoveAtStart() {
        assertEquals(0, tennisViewModel.playerOneScore)
        assertEquals(0, tennisViewModel.playerTwoScore)
    }
}
