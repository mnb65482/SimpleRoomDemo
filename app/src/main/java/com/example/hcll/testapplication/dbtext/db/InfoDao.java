package com.example.hcll.testapplication.dbtext.db;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import com.example.hcll.testapplication.dbtext.entity.InfoEntity;

import java.util.List;

@Dao
public interface InfoDao {

    @Query("SELECT * FROM info")
    List<InfoEntity> getInfoList();

    @Query("SELECT * FROM info where uuid = :id")
    InfoEntity getInfoEntity(String id);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void addInfo(InfoEntity infoEntity);

    @Delete
    void deleteInfo(InfoEntity infoEntity);

    @Update
    int update(InfoEntity infoEntity);

}
