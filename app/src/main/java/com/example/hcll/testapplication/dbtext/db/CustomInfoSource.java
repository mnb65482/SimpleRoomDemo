package com.example.hcll.testapplication.dbtext.db;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import com.example.hcll.testapplication.dbtext.entity.InfoEntity;
import com.example.hcll.testapplication.util.AppExecutor;

import java.util.List;

public class CustomInfoSource implements InfoSource {

    private InfoDao dao;
    private AppExecutor appExecutor;

    public CustomInfoSource(@NonNull InfoDao dao, @NonNull AppExecutor appExecutor) {
        this.dao = dao;
        this.appExecutor = appExecutor;
    }

    @Override
    public void getInfoList(final LoadInfoListCallBack callBack) {
        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                final List<InfoEntity> infoList = dao.getInfoList();
                appExecutor.getMainThread().execute(new Runnable() {
                    @Override
                    public void run() {
                        if (infoList == null || infoList.size() == 0) {
                            callBack.onDataNoAvailable();
                        } else {
                            callBack.onLoadSuccess(infoList);
                        }
                    }
                });

            }
        };

        appExecutor.getDiskIO().execute(runnable);
    }

    @Override
    public void getInfo(String uuid, GetInfoCallBack callBack) {

    }

    @Override
    public void addInfo(final InfoEntity entity) {
        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                dao.addInfo(entity);
            }
        };

        appExecutor.getDiskIO().execute(runnable);
    }

    @Override
    public void deleteInfo(InfoEntity entity) {

    }

    @Override
    public void updateInfo(InfoEntity entity) {

    }
}
