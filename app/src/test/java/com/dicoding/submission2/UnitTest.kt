package com.dicoding.submission2

import com.dicoding.submission2.model.MatchModelResponse
import com.dicoding.submission2.presenter.MatchPresenter
import com.dicoding.submission2.repository.MatchRepo
import com.dicoding.submission2.repository.MatchRepoCallback
import com.dicoding.submission2.view.MatchView
import com.nhaarman.mockito_kotlin.argumentCaptor
import com.nhaarman.mockito_kotlin.eq
import com.nhaarman.mockito_kotlin.verify
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.MockitoAnnotations

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
class UnitTest {
    @Mock
    private lateinit var view: MatchView

    @Mock
    private lateinit var matchRepository: MatchRepo

    @Mock
    private lateinit var matchResponse: MatchModelResponse

    private lateinit var matchPresenter: MatchPresenter

    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)
        matchPresenter = MatchPresenter(view, matchRepository)
    }


    @Test
    fun getMatchLoadedTest() {

        val id = "eventsnextleague.php?id=4329"

        matchPresenter.getData(id)
        argumentCaptor<MatchRepoCallback<MatchModelResponse?>>().apply {

            verify(matchRepository).getMatch(eq(id), capture())
            firstValue.onDataLoaded(matchResponse)
        }

        Mockito.verify(view).onShowLoading()
        Mockito.verify(view).onDataLoaded(matchResponse)
        Mockito.verify(view).onHideLoading()
    }


    @Test
    fun getMatchErrorTest() {

        matchPresenter.getData("")

        argumentCaptor<MatchRepoCallback<MatchModelResponse?>>().apply {

            verify(matchRepository).getMatch(eq(""), capture())
            firstValue.onDataError()
        }

        Mockito.verify(view).onShowLoading()
        Mockito.verify(view).onDataError()
        Mockito.verify(view).onHideLoading()
    }


}
