package com.android.nsystem.testapps.fruit

import com.android.nsystem.testapps.R

/**
 * @author Putra Nugraha (putra.nugraha@dana.id)
 * @version FruitListPresenter, v 0.1 17/12/20 13.16 by Putra Nugraha
 */
class FruitListPresenter(private val view: FruitListContract.View): FruitListContract.Presenter {

    private val fruitMock by lazy {
        listOf(
            Fruit("Banana", R.drawable.banana),
            Fruit("Grapres", R.drawable.grapes),
            Fruit("Pineapple", R.drawable.pineapple),
            Fruit("Strawberry", R.drawable.strawberry),
            Fruit("Watermelon", R.drawable.watermelon)
        )
    }

    override fun getData() {
        view.onSuccessGetData(fruitMock)
    }
}