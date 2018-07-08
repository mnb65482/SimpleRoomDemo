package com.example.hcll.testapplication.dbtext.entity;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;

import java.util.UUID;

@Entity(tableName = "info")
public class InfoEntity {

    @PrimaryKey(autoGenerate = true)
    public int _id;

    @ColumnInfo(name = "uuid")
    private String uuid;

    private String info;

    public InfoEntity(String info) {
        uuid = UUID.randomUUID().toString();
        this.info = info;
    }

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }

    @Override
    public String toString() {
        return String.format("id: %s  ----> info: %s", uuid, info);
    }
}
