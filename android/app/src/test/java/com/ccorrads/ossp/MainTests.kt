package com.ccorrads.ossp

import junit.framework.TestCase
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4

@RunWith(JUnit4::class)
class MainTests : TestCase() {

    lateinit var activity: MainActivity

    @Before
    fun setUpTests() {
        activity = MainActivity()
    }

    @Test
    fun testActivity() {
        assertNotNull(activity)
    }
}