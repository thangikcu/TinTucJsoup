package com.thanggun99.baithi.view.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.thanggun99.baithi.R;
import com.thanggun99.baithi.adapter.TinTucAdapter;
import com.thanggun99.baithi.model.DataManager;
import com.thanggun99.baithi.model.entity.TinTuc;
import com.thanggun99.baithi.util.Utils;
import com.thanggun99.baithi.view.dialog.NotifiDialog;

public class MainActivity extends AppCompatActivity implements DataManager.OnFinishGetDatasListener, TinTucAdapter.OnClickTinTucListener {
    private DataManager dataManager;
    private NotifiDialog notifiDialog;

    private RecyclerView tinTucRecyclerView;
    private TinTucAdapter tinTucAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        findViews();
        initComponents();
        setEvents();
    }

    private void findViews() {
        tinTucRecyclerView = (RecyclerView) findViewById(R.id.recyclerview_tin_tuc);

    }

    private void initComponents() {
        dataManager = new DataManager(this);
        dataManager.setOnFinishGetDatasListener(this);
        dataManager.getDatas();

        notifiDialog = new NotifiDialog(this);
    }

    private void setEvents() {
        tinTucRecyclerView.setLayoutManager(new LinearLayoutManager(this));
    }

    @Override
    public void onNotConnect() {
        notifiDialog.notifi(Utils.getStringByRes(R.string.kiem_tra_ket_noi_mang));
    }

    @Override
    public void onSuccess() {
        tinTucAdapter = new TinTucAdapter(this, dataManager.getTinTucList());
        tinTucAdapter.setOnClickTinTucListener(this);
        tinTucRecyclerView.setAdapter(tinTucAdapter);
    }

    @Override
    public void onFail() {
        notifiDialog.notifi(Utils.getStringByRes(R.string.tai_dư_lieu_that_bai));
    }

    @Override
    public void onClickTinTuc(TinTuc tinTuc) {
        Intent intent = new Intent(this, ChiTietTinTucActivity.class);
        intent.putExtra(TinTuc.TIN_TUC, tinTuc);

        startActivity(intent);
    }
}
