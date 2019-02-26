package com.ccorrads.ossp

import junit.framework.TestCase
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4

@RunWith(JUnit4::class)
class HomeTests : TestCase() {

    lateinit var activity: HomeActivity

    @Before
    fun setUpTests() {
        activity = HomeActivity()
    }

    @Test
    fun testActivity() {
        assertNotNull(activity)
    }
}