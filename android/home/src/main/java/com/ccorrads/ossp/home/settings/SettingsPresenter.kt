package com.ccorrads.ossp.home.settings

import android.content.Context
import android.util.Log
import com.ccorrads.ossp.core.Router
import com.ccorrads.ossp.core.database.dao.AuthDao
import com.ccorrads.ossp.core.database.dao.UserDao
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import org.joda.time.DateTime
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

    override fun init(view: SettingsMvp.View) {
        disposable.add(
            userDao.getCurrentUser()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({ user ->
                    view.setRole(user.role.toString())
                    view.setCreatedDate(DateTime(user.createdDate))
                    view.setEmail(user.username)
                    view.setName(user.fullName)
                },
                    { err ->
                        Log.e("SettingsPresenter", err.message, err)
                    })
        )
    }

    override fun cancel() {
        disposable.clear()
    }
}