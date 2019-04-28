package com.ccorrads.ossp.home

import android.content.Context
import com.ccorrads.ossp.core.database.dao.AuthDao
import com.ccorrads.ossp.core.database.dao.UserDao
import com.ccorrads.ossp.home.settings.SettingsPresenter
import junit.framework.TestCase
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class HomeTests : TestCase() {

    @Mock
    private lateinit var settingsPresenter: SettingsPresenter

    @Mock
    private lateinit var authDao: AuthDao

    @Mock
    private lateinit var userDao: UserDao

    @Mock
    private lateinit var context: Context

    @Before
    fun setUpTests() {
        settingsPresenter = SettingsPresenter(authDao = authDao, userDao = userDao, context = context)
    }

    @Test
    fun testPresenter() {
        assertNotNull(settingsPresenter)
    }
}