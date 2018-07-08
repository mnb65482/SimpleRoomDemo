package com.example.hcll.testapplication.dbtext;

import android.app.ActionBar;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import com.example.hcll.testapplication.R;
import com.example.hcll.testapplication.dbtext.db.CustomInfoSource;
import com.example.hcll.testapplication.dbtext.db.InfoDataBase;
import com.example.hcll.testapplication.dbtext.db.InfoSource;
import com.example.hcll.testapplication.dbtext.entity.InfoEntity;
import com.example.hcll.testapplication.util.AppExecutor;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Executors;

import butterknife.BindView;
import butterknife.ButterKnife;

public class DbListActivity extends AppCompatActivity {
//    @BindView(R.id.dblist_lv)
//    ListView dblistLv;

    private List<InfoEntity> list = new ArrayList<InfoEntity>();
    private ArrayAdapter<InfoEntity> adapter;
    private ListView dblistLv;
    private CustomInfoSource source;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dblist);
//        ButterKnife.bind(this);

        dblistLv = (ListView) findViewById(R.id.dblist_lv);

        source = new CustomInfoSource(InfoDataBase.getInstance(DbListActivity.this).getInfoDao(),
                new AppExecutor(Executors.newSingleThreadExecutor(), Executors.newSingleThreadExecutor(), new AppExecutor.MainThreadExecutor()));

        adapter = new ArrayAdapter<InfoEntity>(this, android.R.layout.simple_list_item_1, list);
//        ArrayAdapter<String> adapter1 = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, new String[]{"123"});
        dblistLv.setAdapter(adapter);

//        List<InfoEntity> infoList = InfoDataBase.getInstance(DbListActivity.this).getInfoDao().getInfoList();
        source.getInfoList(new InfoSource.LoadInfoListCallBack() {
            @Override
            public void onDataNoAvailable() {
            }

            @Override
            public void onLoadSuccess(List<InfoEntity> infos) {
                list.addAll(infos);
                adapter.notifyDataSetChanged();
            }
        });


    }

    public void addInfo(View view) {
        final EditText editText = new EditText(this);
        new AlertDialog.Builder(this).setView(editText).setPositiveButton("确认", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
//                InfoDataBase.getInstance(DbListActivity.this).getInfoDao().addInfo(new InfoEntity(editText.getText().toString()));
//                adapter.notifyDataSetChanged();
//                dialogInterface.dismiss();
                source.addInfo(new InfoEntity(editText.getText().toString()));
                source.getInfoList(new InfoSource.LoadInfoListCallBack() {
                    @Override
                    public void onDataNoAvailable() {
                    }

                    @Override
                    public void onLoadSuccess(List<InfoEntity> infos) {
                        list.clear();
                        list.addAll(infos);
                        adapter.notifyDataSetChanged();
                    }
                });
                dialogInterface.dismiss();
            }
        }).show();
    }

}
