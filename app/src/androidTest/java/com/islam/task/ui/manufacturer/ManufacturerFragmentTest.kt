package com.islam.task.ui.manufacturer

import androidx.fragment.app.testing.FragmentScenario
import androidx.fragment.app.testing.launchFragmentInContainer
import androidx.lifecycle.Lifecycle
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions
import androidx.test.espresso.assertion.ViewAssertions
import androidx.test.espresso.contrib.RecyclerViewActions.actionOnItemAtPosition
import androidx.test.espresso.matcher.ViewMatchers
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.espresso.matcher.ViewMatchers.withText
import androidx.test.internal.runner.junit4.AndroidJUnit4ClassRunner
import com.islam.task.R
import com.islam.task.data.entity.ItemModel
import com.islam.task.ui.adapters.ManufacturerAdapter
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith


@RunWith(AndroidJUnit4ClassRunner::class)
class ManufacturerFragmentTest {

    private lateinit var scenario: FragmentScenario<ManufacturerFragment>

    @Before
    fun setUp() {
        scenario = launchFragmentInContainer(themeResId = R.style.Theme_MyTask)
        scenario.moveToState(Lifecycle.State.STARTED)
    }

    @Test
    fun checkIfListIsVisible_onAppLaunch() {

        onView(withId(R.id.list)).check(ViewAssertions.matches(ViewMatchers.isDisplayed()))

    }


}