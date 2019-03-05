package com.ccorrads.ossp.loginregistration

import com.ccorrads.ossp.core.database.dao.AuthDao
import com.ccorrads.ossp.core.injection.NetworkModule
import com.ccorrads.ossp.core.network.BackendService
import com.ccorrads.ossp.core.network.NetworkUtil
import com.ccorrads.ossp.loginregistration.registration.injection.RegisterPresenter


@RunWith(MockitoJUnitRunner::class)
class RegistrationTests : TestCase() {

    lateinit var presenter: RegisterPresenter

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
        presenter = RegisterPresenter(backendService, rxSchedulers, networkUtil, authDao)
    }

    @Test
    fun testPresenter() {
        assertNotNull(presenter)
    }

}