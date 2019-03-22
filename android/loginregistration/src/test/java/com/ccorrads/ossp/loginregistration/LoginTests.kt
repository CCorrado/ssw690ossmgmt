package com.ccorrads.ossp.loginregistration

import com.ccorrads.ossp.core.database.dao.AuthDao
import com.ccorrads.ossp.core.injection.NetworkModule
import com.ccorrads.ossp.core.network.BackendService
import com.ccorrads.ossp.core.network.NetworkUtil
import com.ccorrads.ossp.loginregistration.injection.LoginRegisterPresenter
import junit.framework.TestCase
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class LoginTests : TestCase() {

    lateinit var presenter: LoginRegisterPresenter

    @Mock
    lateinit var authDao: AuthDao

    @Mock
    lateinit var backendService: BackendService

    @Mock
    lateinit var networkUtil: NetworkUtil

    @Mock
    lateinit var rxSchedulers: NetworkModule.RxSchedulers

    @Before
    fun setUpTests() {
        presenter = LoginRegisterPresenter(networkUtil, backendService, authDao, rxSchedulers)
    }

    @Test
    fun testPresenter() {
        assertNotNull(presenter)
    }

}