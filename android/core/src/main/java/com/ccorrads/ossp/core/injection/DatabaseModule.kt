package com.ccorrads.ossp.core.injection

import android.content.Context
import androidx.room.Room
import com.ccorrads.ossp.core.R
import com.ccorrads.ossp.core.database.OSSPDb
import com.ccorrads.ossp.core.database.dao.AuthDao
import com.ccorrads.ossp.core.database.dao.UserDao
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class DatabaseModule {

    @Provides
    @Singleton
    fun providesDb(context: Context): OSSPDb {
        return Room.databaseBuilder(context, OSSPDb::class.java, context.getString(R.string.databaseName))
            .fallbackToDestructiveMigration()
            .allowMainThreadQueries()
            .build()
    }

    @Provides
    @Singleton
    fun providesAuthDao(osspDb: OSSPDb): AuthDao {
        return osspDb.getAuthDao()
    }

    @Provides
    @Singleton
    fun providesUserDao(osspDb: OSSPDb): UserDao {
        return osspDb.getUserDao()
    }
}