package com.thanggun99.baithi.view.activity;

import android.app.Activity;
import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.thanggun99.baithi.R;
import com.thanggun99.baithi.model.DataManager;
import com.thanggun99.baithi.model.entity.TinTuc;
import com.thanggun99.baithi.util.Utils;
import com.thanggun99.baithi.view.dialog.NotifiDialog;

public class ChiTietTinTucActivity extends Activity implements DataManager.OnFinishGetDatasListener {

    private DataManager dataManager;
    private NotifiDialog notifiDialog;
    private ImageView ivHinhAnh;
    private TextView tvTitle;
    private TextView tvNoiDung;
    private TextView tvMoTa;
    private TinTuc tinTuc;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chi_tiet_tin_tuc);
        tinTuc = (TinTuc) getIntent().getSerializableExtra(TinTuc.TIN_TUC);

        findViews();
        initComponents();
        setEvents();
    }


    private void findViews() {
        ivHinhAnh = (ImageView) findViewById(R.id.iv_hinh);
        tvTitle = (TextView) findViewById(R.id.tv_title);
        tvNoiDung = (TextView) findViewById(R.id.tv_noi_dung);
        tvMoTa = (TextView) findViewById(R.id.tv_mo_ta);

    }

    private void initComponents() {
        dataManager = new DataManager(this);
        dataManager.setOnFinishGetDatasListener(this);

        notifiDialog = new NotifiDialog(this);
    }

    private void setEvents() {
        tvNoiDung.setMovementMethod(new ScrollingMovementMethod());
        if (tinTuc != null) {
            dataManager.getDatas(tinTuc.getChiTietUrl());

            tvTitle.setText(tinTuc.getTitle());
            tvMoTa.setText(tinTuc.getMoTa());

            Glide.with(this)
                    .load(tinTuc.getHinhAnh())
                    .diskCacheStrategy(DiskCacheStrategy.NONE)
                    .skipMemoryCache(true)
                    .into(ivHinhAnh);
        }
    }

    @Override
    public void onNotConnect() {
        notifiDialog.notifi(Utils.getStringByRes(R.string.kiem_tra_ket_noi_mang));
    }

    @Override
    public void onSuccess() {
        tvNoiDung.setText(dataManager.getNoiDung());
    }

    @Override
    public void onFail() {
        notifiDialog.notifi(Utils.getStringByRes(R.string.tai_dư_lieu_that_bai));
    }
}
