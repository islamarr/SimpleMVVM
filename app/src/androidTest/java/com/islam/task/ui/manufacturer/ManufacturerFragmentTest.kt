package com.islam.task.ui.manufacturer

import androidx.fragment.app.testing.FragmentScenario
import androidx.fragment.app.testing.launchFragmentInContainer
import androidx.lifecycle.Lifecycle
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.assertion.ViewAssertions
import androidx.test.espresso.contrib.RecyclerViewActions
import androidx.test.espresso.contrib.RecyclerViewActions.scrollToPosition
import androidx.test.espresso.matcher.ViewMatchers
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.ext.junit.rules.ActivityScenarioRule
import androidx.test.internal.runner.junit4.AndroidJUnit4ClassRunner
import com.codingwithmitch.espressouitestexamples.util.EspressoIdlingResourceRule
import com.islam.task.R
import com.islam.task.data.entity.ItemModel
import com.islam.task.ui.MainActivity
import com.islam.task.ui.adapters.ManufacturerAdapter
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith


@RunWith(AndroidJUnit4ClassRunner::class)
class ManufacturerFragmentTest {

    @get:Rule
    val activityRule = ActivityScenarioRule(MainActivity::class.java)

    @get: Rule
    val espressoIdlingResoureRule = EspressoIdlingResourceRule()

    private lateinit var scenario: FragmentScenario<ManufacturerFragment>

    private val recyclerView: Int = R.id.list

    val Item_Postition = 8
    val items = listOf(
        ItemModel(key = "107", value = "Bentley"),
        ItemModel(key = "130", value = "BMW"),
        ItemModel(key = "125", value = "Borgward")
    )

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


        onView(withId(this.recyclerView)).perform(
            scrollToPosition<ManufacturerAdapter.ViewHolder>(
                Item_Postition
            )
        )
        /*    onView(withId(this.recyclerView)).perform(
                RecyclerViewActions.actionOnItemAtPosition<ManufacturerAdapter.ViewHolder>(
                    Item_Postition,
                    click()
                )
            )

             onView(withId(R.id.toolbar))
                 .check(ViewAssertions.matches(withText("Car Types >> BMW")))*/
    }


}