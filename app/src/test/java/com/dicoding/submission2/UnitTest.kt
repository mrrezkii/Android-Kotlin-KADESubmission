package com.dicoding.submission2

import com.dicoding.submission2.presenter.DetailPresenter
import com.dicoding.submission2.presenter.FavoritePresenter
import com.dicoding.submission2.presenter.MatchPresenter
import com.dicoding.submission2.repository.DetailRepo
import com.dicoding.submission2.repository.FavoriteRepo
import com.dicoding.submission2.repository.MatchRepo
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
    private lateinit var matchPresenter: MatchPresenter
    private lateinit var detailPresenter: DetailPresenter
    private lateinit var favPresenter: FavoritePresenter

    @Mock
    private lateinit var repo: MatchRepo
    @Mock
    private lateinit var detRepo: DetailRepo
    @Mock
    private lateinit var favRepo: FavoriteRepo

    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)
        matchPresenter = MatchPresenter("eventspastleague.php?id=4328", repo)
        detailPresenter = DetailPresenter("lookupevent.php?id=576504", detRepo)
        favPresenter = FavoritePresenter(favRepo)
    }


    @Test
    fun testGetMatchList() {
        matchPresenter.getData()
        Mockito.verify(repo).getMatch("eventspastleague.php?id=4328")
    }

    @Test
    fun testGetTeamDetail() {
        detailPresenter.getData()
        Mockito.verify(detRepo).getDetail("lookupevent.php?id=576504")
    }

    @Test
    fun testGetFavorite() {
        favPresenter.getData()
        Mockito.verify(favRepo).getFavorite()
    }
}
