package com.android.nsystem.testapps.utils

import androidx.annotation.VisibleForTesting
import androidx.test.espresso.idling.CountingIdlingResource

/**
 * @author Putra Nugraha (putra.nugraha@dana.id)
 * @version EspressoIdlingResource, v 0.1 20/12/20 12.34 by Putra Nugraha
 */
@VisibleForTesting(otherwise = VisibleForTesting.NONE)
object EspressoIdlingResource {

    private const val RESOURCE = "GLOBAL"

    @JvmField
    val countingIdlingResouce = CountingIdlingResource(RESOURCE)

    fun increment() {
        countingIdlingResouce.increment()
    }

    fun decrement() {
        if (!countingIdlingResouce.isIdleNow) {
            countingIdlingResouce.decrement()
        }
    }
}