package com.curso.android.app.practica.fgbapp.view

import androidx.test.espresso.Espresso
import androidx.test.espresso.action.ViewActions
import androidx.test.espresso.assertion.ViewAssertions
import androidx.test.espresso.matcher.ViewMatchers
import androidx.test.ext.junit.rules.ActivityScenarioRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.curso.android.app.practica.fgbapp.R
import org.junit.Assert.*

import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class MainActivityTest {

    @get:Rule
    var rule: ActivityScenarioRule<*> = ActivityScenarioRule(MainActivity::class.java)

    @Test
    fun mainActivity_compareEmptyStrings() {
        Espresso.onView(
            ViewMatchers.withId(R.id.compare_btn)
        ).perform(
            ViewActions.click()
        )

        Espresso.onView(
            ViewMatchers.withId(R.id.result_txt)
        ).check(
            ViewAssertions.matches(
                ViewMatchers.withText("Error: Complete ambos campos.")
            )
        )
    }

    @Test
    fun mainActivity_compareEqualStrings() {
        Espresso.onView(
            ViewMatchers.withId(R.id.str1)
        ).perform(
            ViewActions.typeText("qwerty")
        )

        Espresso.onView(
            ViewMatchers.withId(R.id.str2)
        ).perform(
            ViewActions.typeText("qwerty")
        )

        Espresso.onView(
            ViewMatchers.withId(R.id.compare_btn)
        ).perform(
            ViewActions.click()
        )

        Espresso.onView(
            ViewMatchers.withId(R.id.result_txt)
        ).check(
            ViewAssertions.matches(
                ViewMatchers.withText(R.string.result_ok)
            )
        ).check(
            ViewAssertions.matches(
                ViewMatchers.hasTextColor(R.color.result_ok)
            )
        )
    }

    @Test
    fun mainActivity_compareDifferentStrings() {
        Espresso.onView(
            ViewMatchers.withId(R.id.str1)
        ).perform(
            ViewActions.typeText("qwerty")
        )

        Espresso.onView(
            ViewMatchers.withId(R.id.str2)
        ).perform(
            ViewActions.typeText("dvorak")
        )

        Espresso.onView(
            ViewMatchers.withId(R.id.compare_btn)
        ).perform(
            ViewActions.click()
        )

        Espresso.onView(
            ViewMatchers.withId(R.id.result_txt)
        ).check(
            ViewAssertions.matches(
                ViewMatchers.withText(R.string.result_fail)
            )
        ).check(
            ViewAssertions.matches(
                ViewMatchers.hasTextColor(R.color.result_fail)
            )
        )
    }
}