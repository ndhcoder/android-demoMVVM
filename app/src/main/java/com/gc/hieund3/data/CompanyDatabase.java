package com.gc.hieund3.data;

import android.content.Context;

import com.gc.hieund3.data.dao.UserDao;
import com.gc.hieund3.data.model.User;
import com.gc.hieund3.utils.Const;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

@Database(entities = {User.class}, version = 1, exportSchema = false)
public abstract class CompanyDatabase extends RoomDatabase {

    public abstract UserDao userDao();

    private static volatile CompanyDatabase INSTANCE;

    public static CompanyDatabase getDatabase(final Context context) {
        if (INSTANCE == null) {
            synchronized (CompanyDatabase.class) {
                if (INSTANCE == null) {
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                            CompanyDatabase.class, Const.SQLITE_DATABASE_NAME)
                            .allowMainThreadQueries()
                            .fallbackToDestructiveMigration()
                            .build();
                }
            }
        }

        return INSTANCE;
    }
}

