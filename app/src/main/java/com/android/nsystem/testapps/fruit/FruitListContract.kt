package com.android.nsystem.testapps.fruit

/**
 * @author Putra Nugraha (putra.nugraha@dana.id)
 * @version FruitListContract, v 0.1 17/12/20 13.14 by Putra Nugraha
 */
interface FruitListContract {

    interface View {

        fun onSuccessGetData(fruits: List<Fruit>)
    }

    interface Presenter {

        fun getData()
    }
}