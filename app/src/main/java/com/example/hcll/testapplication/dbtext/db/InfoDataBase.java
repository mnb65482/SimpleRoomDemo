package com.example.hcll.testapplication.dbtext.db;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.content.Context;

import com.example.hcll.testapplication.dbtext.entity.InfoEntity;

@Database(entities = {InfoEntity.class}, version = 1, exportSchema = false)
public abstract class InfoDataBase extends RoomDatabase {

    private static InfoDataBase INSTANCE;

    public static InfoDataBase getInstance(Context context) {
        if (INSTANCE == null) {
            synchronized (InfoDataBase.class) {
                if (INSTANCE == null) {
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(), InfoDataBase.class, "info.db").build();
                }
            }
        }
        return INSTANCE;
    }

    public abstract InfoDao getInfoDao();
}
