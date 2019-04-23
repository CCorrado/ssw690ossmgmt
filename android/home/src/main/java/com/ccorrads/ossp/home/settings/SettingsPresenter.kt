package com.ccorrads.ossp.home.settings

import android.content.Context
import com.ccorrads.ossp.core.Router
import com.ccorrads.ossp.core.database.dao.AuthDao
import com.ccorrads.ossp.core.database.dao.UserDao
import io.reactivex.disposables.CompositeDisposable
import javax.inject.Inject

class SettingsPresenter
@Inject constructor(
    private val userDao: UserDao,
    private val authDao: AuthDao,
    private val context: Context
) : SettingsMvp.Presenter {

    private val disposable: CompositeDisposable = CompositeDisposable()

    override fun logout(router: Router?) {
        userDao.clear()
        authDao.clear()
        router?.logout(context)
    }

    override fun cancel() {
        disposable.clear()
    }
}