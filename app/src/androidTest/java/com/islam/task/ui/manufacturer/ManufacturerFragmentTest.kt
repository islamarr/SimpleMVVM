package com.islam.task.ui.manufacturer

import androidx.fragment.app.testing.FragmentScenario
import androidx.fragment.app.testing.launchFragmentInContainer
import androidx.lifecycle.Lifecycle
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.assertion.ViewAssertions
import androidx.test.espresso.contrib.RecyclerViewActions
import androidx.test.espresso.matcher.ViewMatchers
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.ext.junit.rules.ActivityScenarioRule
import androidx.test.internal.runner.junit4.AndroidJUnit4ClassRunner
import com.islam.task.R
import com.islam.task.ui.MainActivity
import com.islam.task.ui.adapters.ManufacturerAdapter
import com.islam.task.utils.EspressoIdlingResourceRule
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mockito.mock
import org.mockito.Mockito.verify


@RunWith(AndroidJUnit4ClassRunner::class)
class ManufacturerFragmentTest {

    @get:Rule
    val activityRule = ActivityScenarioRule(MainActivity::class.java)

    @get: Rule
    val espressoIdlingResoureRule = EspressoIdlingResourceRule()

    private lateinit var scenario: FragmentScenario<ManufacturerFragment>

    private val recyclerView: Int = R.id.list

    val Item_Postition = 0

    @Before
    fun setUp() {
        scenario = launchFragmentInContainer(themeResId = R.style.Theme_MyTask)
        scenario.moveToState(Lifecycle.State.STARTED)
    }

    @Test
    fun checkIfListIsVisible_onAppLaunch() {

        onView(withId(this.recyclerView)).check(ViewAssertions.matches(ViewMatchers.isDisplayed()))

    }

    @Test
    fun test_selectListItem_InList() {

        val navController = mock(NavController::class.java)
        scenario.onFragment {
            Navigation.setViewNavController(it.view!!, navController)
        }

        Thread.sleep(4000)

        onView(withId(this.recyclerView)).perform(
            RecyclerViewActions.actionOnItemAtPosition<ManufacturerAdapter.ViewHolder>(
                Item_Postition,
                click()
            )
        )

        Thread.sleep(2000)

        verify(navController).navigate(R.id.action_manufacturerFragment_to_carTypesFragment)

    }


}