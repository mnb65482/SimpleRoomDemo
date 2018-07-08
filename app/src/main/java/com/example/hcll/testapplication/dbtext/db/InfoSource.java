package com.example.hcll.testapplication.dbtext.db;

import com.example.hcll.testapplication.dbtext.entity.InfoEntity;

import java.util.List;

public interface InfoSource {

    interface LoadInfoListCallBack {

        void onDataNoAvailable();

        void onLoadSuccess(List<InfoEntity> list);
    }

    interface GetInfoCallBack {
        void onDataNoAvailable();

        void onLoadSuccess(InfoEntity entity);
    }

    void getInfoList(LoadInfoListCallBack callBack);

    void getInfo(String uuid, GetInfoCallBack callBack);

    void addInfo(InfoEntity entity);

    void deleteInfo(InfoEntity entity);

    void updateInfo(InfoEntity entity);

}
