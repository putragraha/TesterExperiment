package com.android.nsystem.testapps.fruit

import com.nhaarman.mockitokotlin2.any
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.InjectMocks
import org.mockito.Mock
import org.mockito.Mockito.verify
import org.mockito.junit.MockitoJUnitRunner

/**
 * @author Putra Nugraha (putra.nugraha@dana.id)
 * @version FruitListPresenterTest, v 0.1 19/12/20 18.57 by Putra Nugraha
 */
@RunWith(MockitoJUnitRunner::class)
class FruitListPresenterTest {

    @InjectMocks
    lateinit var presenter: FruitListPresenter

    @Mock
    lateinit var view: FruitListContract.View

    @Test
    fun getData_shouldCall_onSuccessGetData_inView() {
        // given

        // when
        presenter.getData()

        // then
        verify(view).onSuccessGetData(any())
    }
}